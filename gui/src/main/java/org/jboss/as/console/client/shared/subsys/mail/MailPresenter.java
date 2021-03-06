package org.jboss.as.console.client.shared.subsys.mail;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.Place;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import org.jboss.as.console.client.Console;
import org.jboss.as.console.client.core.NameTokens;
import org.jboss.as.console.client.domain.model.SimpleCallback;
import org.jboss.as.console.client.shared.dispatch.DispatchAsync;
import org.jboss.as.console.client.shared.dispatch.impl.DMRAction;
import org.jboss.as.console.client.shared.dispatch.impl.DMRResponse;
import org.jboss.as.console.client.shared.subsys.Baseadress;
import org.jboss.as.console.client.shared.subsys.RevealStrategy;
import org.jboss.as.console.client.shared.subsys.jca.wizard.NewDatasourceWizard;
import org.jboss.as.console.client.shared.subsys.jpa.model.JpaSubsystem;
import org.jboss.as.console.client.widgets.forms.ApplicationMetaData;
import org.jboss.as.console.client.widgets.forms.BeanMetaData;
import org.jboss.as.console.client.widgets.forms.EntityAdapter;
import org.jboss.ballroom.client.widgets.window.DefaultWindow;
import org.jboss.dmr.client.ModelNode;
import org.jboss.dmr.client.Property;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.jboss.dmr.client.ModelDescriptionConstants.*;

/**
 * @author Heiko Braun
 * @date 11/28/11
 */
public class MailPresenter extends Presenter<MailPresenter.MyView, MailPresenter.MyProxy> {

    private final PlaceManager placeManager;

    private RevealStrategy revealStrategy;
    private ApplicationMetaData metaData;
    private DispatchAsync dispatcher;
    private EntityAdapter<MailSession> adapter;
    private BeanMetaData beanMetaData;
    private DefaultWindow window;

    @ProxyCodeSplit
    @NameToken(NameTokens.MailPresenter)
    public interface MyProxy extends Proxy<MailPresenter>, Place {
    }

    public interface MyView extends View {
        void setPresenter(MailPresenter presenter);
        void updateFrom(List<MailSession> list);
    }

    @Inject
    public MailPresenter(
            EventBus eventBus, MyView view, MyProxy proxy,
            PlaceManager placeManager,
            DispatchAsync dispatcher,
            RevealStrategy revealStrategy,
            ApplicationMetaData metaData) {
        super(eventBus, view, proxy);

        this.placeManager = placeManager;

        this.revealStrategy = revealStrategy;
        this.metaData = metaData;
        this.dispatcher = dispatcher;
        this.beanMetaData = metaData.getBeanMetaData(MailSession.class);
        this.adapter = new EntityAdapter<MailSession>(MailSession.class, metaData);

    }

    @Override
    protected void onBind() {
        super.onBind();
        getView().setPresenter(this);
    }


    @Override
    protected void onReset() {
        super.onReset();
        loadMailSessions();
    }

    public void launchNewSessionWizard() {
        window = new DefaultWindow(Console.MESSAGES.createTitle("Datasource"));
        window.setWidth(480);
        window.setHeight(360);

        window.setWidget(
                new NewMailSessionWizard(this).asWidget()
        );

        window.setGlassEnabled(true);
        window.center();
    }

    // TODO: https://issues.jboss.org/browse/AS7-2814
    private void loadMailSessions() {

        ModelNode operation = beanMetaData.getAddress().asSubresource(Baseadress.get());
        operation.get(OP).set(READ_CHILDREN_RESOURCES_OPERATION);

        dispatcher.execute(new DMRAction(operation), new SimpleCallback<DMRResponse>() {
            @Override
            public void onSuccess(DMRResponse result) {
                ModelNode response  = result.get();

                if(response.isFailure())
                {
                    Console.error("Failed to load Mail sessions");
                }
                else
                {
                    List<Property> items = response.get(RESULT).asPropertyList();
                    List<MailSession> sessions = new ArrayList<MailSession>(items.size());
                    for(Property item : items)
                    {
                        ModelNode model = item.getValue();
                        MailSession mailSession = adapter.fromDMR(model);
                        sessions.add(mailSession);
                    }

                    getView().updateFrom(sessions);
                }

            }
        });
    }

    @Override
    protected void revealInParent() {
        revealStrategy.revealInParent(this);
    }

    public void closeDialoge() {
        window.hide();
    }


    public void onCreateSession(final MailSession entity) {

        closeDialoge();

        ModelNode address = beanMetaData.getAddress().asResource(Baseadress.get(), entity.getJndiName());

        ModelNode operation = adapter.fromEntity(entity);
        operation.get(ADDRESS).set(address.get(ADDRESS));
        operation.get(OP).set(ADD);

        System.out.println(operation);

        dispatcher.execute(new DMRAction(operation), new SimpleCallback<DMRResponse>() {
            @Override
            public void onSuccess(DMRResponse result) {
                ModelNode response  = result.get();

                if(response.isFailure())
                {
                    Console.error("Failed to create mail session");
                }
                else
                {
                    Console.info("Success: Added mail session "+entity.getJndiName());
                }

                loadMailSessions();
            }
        });
    }

    public void onDelete(final MailSession entity) {
        ModelNode operation = beanMetaData.getAddress().asResource(Baseadress.get(), entity.getJndiName());
        operation.get(OP).set(REMOVE);

        System.out.println(operation);

        dispatcher.execute(new DMRAction(operation), new SimpleCallback<DMRResponse>() {
            @Override
            public void onSuccess(DMRResponse result) {
                ModelNode response  = result.get();

                if(response.isFailure())
                {
                    Console.error("Failed to remove mail session", response.get("failure-description").asString());
                }
                else
                {
                    Console.info("Success: Removed mail session "+entity.getJndiName());
                }

                loadMailSessions();
            }
        });
    }

    public void onSave(final MailSession editedEntity, Map<String, Object> changeset) {
        ModelNode operation = adapter.fromChangeset(changeset, beanMetaData.getAddress().asResource());

        dispatcher.execute(new DMRAction(operation), new SimpleCallback<DMRResponse>() {
            @Override
            public void onSuccess(DMRResponse result) {
                ModelNode response  = result.get();

                if(response.isFailure())
                {
                    Console.error("Failed to update mail session subsystem");
                }
                else
                {
                    Console.info("Success: Update mail session "+editedEntity.getJndiName());
                }

                loadMailSessions();
            }
        });
    }
}
