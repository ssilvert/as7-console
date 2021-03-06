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

package org.jboss.as.console.client.domain.hosts;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.Place;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import org.jboss.as.console.client.Console;
import org.jboss.as.console.client.core.MainLayoutPresenter;
import org.jboss.as.console.client.core.NameTokens;
import org.jboss.as.console.client.domain.events.HostSelectionEvent;
import org.jboss.as.console.client.domain.model.Host;
import org.jboss.as.console.client.domain.model.HostInformationStore;
import org.jboss.as.console.client.domain.model.SimpleCallback;
import org.jboss.as.console.client.shared.state.CurrentHostSelection;
import org.jboss.ballroom.client.layout.LHSHighlightEvent;

import java.util.List;

/**
 * @author Heiko Braun
 * @date 3/2/11
 */
public class HostMgmtPresenter
        extends Presenter<HostMgmtPresenter.MyView, HostMgmtPresenter.MyProxy>
        implements HostSelectionEvent.HostSelectionListener  {

    private final PlaceManager placeManager;

    private HostInformationStore hostInfoStore;
    private CurrentHostSelection hostSelection;
    private boolean hasBeenRevealed;

    @ContentSlot
    public static final GwtEvent.Type<RevealContentHandler<?>> TYPE_MainContent = new GwtEvent.Type<RevealContentHandler<?>>();

    @ProxyCodeSplit
    @NameToken(NameTokens.HostMgmtPresenter)
    public interface MyProxy extends Proxy<HostMgmtPresenter>, Place {
    }

    public interface MyView extends View {
        void setPresenter(HostMgmtPresenter presenter);
        void updateHosts(List<Host> hosts);
    }

    @Inject
    public HostMgmtPresenter(
            EventBus eventBus, MyView view, MyProxy proxy,
            PlaceManager placeManager,
            HostInformationStore hostInfoStore, CurrentHostSelection hostSelection) {
        super(eventBus, view, proxy);

        this.placeManager = placeManager;
        this.hostInfoStore = hostInfoStore;
        this.hostSelection = hostSelection;
    }

    @Override
    protected void onBind() {
        super.onBind();
        getView().setPresenter(this);
        getEventBus().addHandler(HostSelectionEvent.TYPE, this);
    }

    @Override
    protected void onReset() {
        super.onReset();

        Console.MODULES.getHeader().highlight(NameTokens.HostMgmtPresenter);

        // first request, select default contents
        if(!hasBeenRevealed &&
                NameTokens.HostMgmtPresenter.equals(placeManager.getCurrentPlaceRequest().getNameToken()))
        {


            placeManager.revealRelativePlace(
                    new PlaceRequest(NameTokens.ServerPresenter)
            );
            hasBeenRevealed = true;


            //  highlight LHS nav
            Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
                @Override
                public void execute() {
                    getEventBus().fireEvent(
                            new LHSHighlightEvent(null, Console.CONSTANTS.common_label_serverConfigs(), "hosts")

                    );
                }
            });
        }


        hostInfoStore.getHosts(new SimpleCallback<List<Host>>() {
            @Override
            public void onSuccess(List<Host> hosts) {
                if(!hostSelection.isSet())
                    selectDefaultHost(hosts);
                getView().updateHosts(hosts);
            }
        });
    }

    private void selectDefaultHost(List<Host> hosts) {
        String name = hosts.get(0).getName();
        System.out.println("Default host selection: "+name);
        hostSelection.setName(name);
        getEventBus().fireEvent(new HostSelectionEvent(name));
    }

    @Override
    protected void revealInParent() {
        RevealContentEvent.fire(getEventBus(), MainLayoutPresenter.TYPE_MainContent, this);
    }

    @Override
    public void onHostSelection(String hostName) {
        hostSelection.setName(hostName);
    }
}
