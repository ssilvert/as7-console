package org.jboss.as.console.client.domain.hosts;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import org.jboss.as.console.client.Console;
import org.jboss.as.console.client.domain.events.HostSelectionEvent;
import org.jboss.as.console.client.shared.state.CurrentHostSelection;
import org.jboss.ballroom.client.widgets.forms.ComboBox;

import java.util.List;

/**
 * @author Heiko Braun
 * @date 11/2/11
 */
public class HostSelector {

    private ComboBox hosts;

    public Widget asWidget() {

        HorizontalPanel layout = new HorizontalPanel();
        layout.setStyleName("fill-layout-width");

        layout.getElement().setAttribute("style","padding:4px;");
        hosts = new ComboBox();
        hosts.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(final ValueChangeEvent<String> event) {

                Scheduler.get().scheduleEntry(new Scheduler.ScheduledCommand() {
                    @Override
                    public void execute() {
                        Console.MODULES.getEventBus().fireEvent(new HostSelectionEvent(event.getValue()));
                    }
                });
            }
        });

        Label hostLabel = new Label("Host:");
        hostLabel.setStyleName("header-label");
        layout.add(hostLabel);
        Widget hWidget = hosts.asWidget();
        layout.add(hWidget);

        // combo box use all available space
        hWidget.getElement().getParentElement().setAttribute("width", "100%");

        return layout;
    }

    public void setHosts(List<String> hostNames)
    {
        hosts.setValues(hostNames);

        CurrentHostSelection hostSelection = Console.MODULES.getCurrentSelectedHost();
        if(hostSelection.isSet())
        {
            int i = 0;
            for(String name : hostNames)
            {
                if(name.equals(hostSelection.getName()))
                {
                    setItemSelected(i, true);
                    break;
                }
                i++;
            }
        }

    }

    public void setItemSelected(int item, boolean b) {
        hosts.setItemSelected(item, b);
    }
}
