/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU Lesser General Public License, v. 2.1.
 * This program is distributed in the hope that it will be useful, but WITHOUT A
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License,
 * v.2.1 along with this distribution; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301, USA.
 */

package org.jboss.as.console.client.shared.subsys.web;

import com.google.gwt.cell.client.ImageResourceCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import org.jboss.as.console.client.shared.help.FormHelpPanel;
import org.jboss.as.console.client.shared.subsys.Baseadress;
import org.jboss.as.console.client.shared.subsys.web.model.HttpConnector;
import org.jboss.as.console.client.widgets.forms.FormToolStrip;
import org.jboss.ballroom.client.widgets.forms.CheckBoxItem;
import org.jboss.ballroom.client.widgets.forms.ComboBoxItem;
import org.jboss.ballroom.client.widgets.forms.Form;
import org.jboss.ballroom.client.widgets.forms.TextBoxItem;
import org.jboss.ballroom.client.widgets.forms.TextItem;
import org.jboss.ballroom.client.widgets.icons.Icons;
import org.jboss.ballroom.client.widgets.tables.DefaultCellTable;
import org.jboss.ballroom.client.widgets.tools.ToolButton;
import org.jboss.ballroom.client.widgets.window.Feedback;
import org.jboss.dmr.client.ModelNode;

import java.util.List;
import java.util.Map;

/**
 * @author Heiko Braun
 * @date 5/11/11
 */
public class ConnectorList {

    private DefaultCellTable<HttpConnector> connectorTable;
    private WebPresenter presenter;
    private Form<HttpConnector> form;

    public ConnectorList(WebPresenter presenter) {
        this.presenter = presenter;
    }

    Widget asWidget() {

        VerticalPanel layout = new VerticalPanel();

        form = new Form<HttpConnector>(HttpConnector.class);
        form.setNumColumns(2);

        FormToolStrip<HttpConnector> toolstrip = new FormToolStrip<HttpConnector>(
                form,
                new FormToolStrip.FormCallback<HttpConnector>() {
                    @Override
                    public void onSave(Map<String, Object> changeset) {
                        presenter.onSaveConnector(form.getEditedEntity().getName(), changeset);
                    }

                    @Override
                    public void onDelete(final HttpConnector entity) {
                        presenter.onDeleteConnector(entity.getName());
                    }
                }
        );

        toolstrip.addToolButtonRight(new ToolButton("Add", new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                presenter.launchConnectorDialogue();
            }
        }));


        layout.add(toolstrip.asWidget());

        // ----


        connectorTable = new DefaultCellTable<HttpConnector>(10);


        Column<HttpConnector, String> nameColumn = new Column<HttpConnector, String>(new TextCell()) {
            @Override
            public String getValue(HttpConnector object) {
                return object.getName();
            }
        };


        Column<HttpConnector, String> protocolColumn = new Column<HttpConnector, String>(new TextCell()) {
            @Override
            public String getValue(HttpConnector object) {
                return object.getProtocol();
            }
        };


        Column<HttpConnector, ImageResource> statusColumn =
                new Column<HttpConnector, ImageResource>(new ImageResourceCell()) {
                    @Override
                    public ImageResource getValue(HttpConnector connector) {

                        ImageResource res = null;

                        if(connector.isEnabled())
                            res = Icons.INSTANCE.statusGreen_small();
                        else
                            res = Icons.INSTANCE.statusRed_small();

                        return res;
                    }
                };

        connectorTable.addColumn(nameColumn, "Name");
        connectorTable.addColumn(protocolColumn, "Protocol");
        connectorTable.addColumn(statusColumn, "Enabled?");

        layout.add(connectorTable);


        // ---



        TextItem name = new TextItem("name", "Name");
        TextBoxItem socketBinding = new TextBoxItem("socketBinding", "Socket Binding");

        ComboBoxItem protocol = new ComboBoxItem("protocol", "Protocol");
        ComboBoxItem scheme = new ComboBoxItem("scheme", "Scheme");

        protocol.setDefaultToFirstOption(true);
        protocol.setValueMap(new String[]{"HTTP/1.1", "AJP/1.3"});

        scheme.setDefaultToFirstOption(true);
        scheme.setValueMap(new String[]{"http", "https"});

        CheckBoxItem state = new CheckBoxItem("enabled", "Enabled?");

        form.setFields(name, socketBinding, protocol, scheme, state);
        form.bind(connectorTable);

        final FormHelpPanel helpPanel = new FormHelpPanel(
                new FormHelpPanel.AddressCallback() {
                    @Override
                    public ModelNode getAddress() {
                        ModelNode address = Baseadress.get();
                        address.add("subsystem", "web");
                        address.add("connector", "*");
                        return address;
                    }
                }, form
        );
        layout.add(helpPanel.asWidget());

        layout.add(form.asWidget());

        return layout;
    }

    public void setConnectors(List<HttpConnector> connectors) {

        connectorTable.setRowCount(connectors.size(), true);
        connectorTable.setRowData(0, connectors);

        if(!connectors.isEmpty())
            connectorTable.getSelectionModel().setSelected(connectors.get(0), true);

        form.setEnabled(false);
    }

    public void setEnabled(boolean b) {
        form.setEnabled(b);
    }
}
