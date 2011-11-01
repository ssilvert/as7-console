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
package org.jboss.ballroom.client.widgets.forms;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import org.jboss.as.console.client.shared.BeanFactory;
import org.jboss.ballroom.client.widgets.window.DefaultWindow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * FormItem that wraps a ListEditor table.
 * 
 * @author Stan Silvert ssilvert@redhat.com (C) 2011 Red Hat Inc.
 */
public class ListEditorFormItem extends FormItem<List<String>> implements ListManagement<String> {
    private static BeanFactory factory = GWT.create(BeanFactory.class);
    
    protected ListEditor listEditor;
    protected List<String> value = Collections.EMPTY_LIST;
    
    protected DefaultWindow addItemDialog;
    protected String addDialogTitle;
    
    protected NewListItemWizard newListItemWizard;
    protected List<String> availableChoices = Collections.EMPTY_LIST;
    
    /**
     * Create a new ListEditorFormItem.
     * 
     * @param name The name of the FormItem.
     * @param title The label that will be displayed with the editor.
     * @param addDialogTitle The title shown when the Add button is pressed.
     * @param rows The max number of rows in the PropertyEditor.
     * @param limitChoices If <code>true</code> choices for new items will be limited to values provided
     *                     in the setAvailableChoices() method.  If <code>false</code> the user may add any String value
     *                     to the list.
     */
    public ListEditorFormItem(String name, String title, String addDialogTitle, int rows, boolean limitChoices) {
        super(name, title);
        this.listEditor = new ListEditor(this, rows);
        this.addDialogTitle = addDialogTitle;
        this.newListItemWizard = new NewListItemWizard(this, limitChoices);
    }
    
    /**
     * This is the full list of available choices.  The list may be
     */
    public void setAvailableChoices(List<String> availableChoices) {
        this.availableChoices = availableChoices;
    }
    
    @Override
    public Widget asWidget() {
        return this.listEditor.asWidget();
    }

    @Override
    public void clearValue() {
        // do nothing
    }

    @Override
    public void setEnabled(boolean isEnabled) {
        this.listEditor.setEnabled(isEnabled);
    }

    @Override
    public boolean validate(List<String> value) {
        return true;
    }

    @Override
    public List<String> getValue() {
        return this.value;
    }

    @Override
    public void setValue(List<String> items) {
        // clone the item so that you can cancel the edit
        List<String> itemsClone = new ArrayList<String>(items.size());
        itemsClone.addAll(items);

        this.value = itemsClone;
        this.listEditor.setList(itemsClone);
    }

    @Override
    public void closeNewItemDialoge() {
        addItemDialog.hide();
    }

    @Override
    public void launchNewItemDialoge() {
        addItemDialog = new DefaultWindow(addDialogTitle);
        addItemDialog.setWidth(320);
        addItemDialog.setHeight(240);
        addItemDialog.setWidget(newListItemWizard.asWidget());
        addItemDialog.setGlassEnabled(true);
        addItemDialog.center();
        
        // create list containing only choices not yet in the list
        List<String> choicesSubset = new ArrayList<String>(this.availableChoices.size());
        choicesSubset.addAll(this.availableChoices);
        choicesSubset.removeAll(value);
        newListItemWizard.setChoices(choicesSubset);
    }
    
    @Override
    public void onCreateItem(String item) {
        this.value.add(item);
        this.listEditor.setList(value);
        setModified(true);
        closeNewItemDialoge();
    }

    @Override
    public void onDeleteItem(String item) {
        this.value.remove(item);
        this.listEditor.setList(value);
        setModified(true);
    }
}
