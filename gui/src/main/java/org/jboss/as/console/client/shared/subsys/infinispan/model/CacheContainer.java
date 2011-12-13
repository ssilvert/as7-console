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
package org.jboss.as.console.client.shared.subsys.infinispan.model;

import java.util.List;
import org.jboss.as.console.client.shared.viewframework.NamedEntity;
import org.jboss.as.console.client.widgets.forms.Address;
import org.jboss.as.console.client.widgets.forms.Binding;
import org.jboss.as.console.client.widgets.forms.FormItem;

/**
 *
 * @author Stan Silvert ssilvert@redhat.com (C) 2011 Red Hat Inc.
 */
@Address("/subsystem=infinispan/cache-container={0}")
public interface CacheContainer extends NamedEntity {
    @Override
    @Binding(detypedName="name", key=true)
    @FormItem(defaultValue="",
              localLabel="common_label_name",
              required=true,
              formItemTypeForEdit="TEXT",
              formItemTypeForAdd="TEXT_BOX")
    public String getName();
    @Override
    public void setName(String name);
    
    @Binding(detypedName = "jndi-name")
    @FormItem(localLabel="subsys_infinispan_jndiName",
            required=false,
            formItemTypeForEdit="TEXT_BOX",
            formItemTypeForAdd="TEXT_BOX")    
    String getJndiName();
    void setJndiName(String jndiName);
    
    @Binding(detypedName= "default-cache")
    @FormItem(defaultValue="",
            localLabel="subsys_infinispan_default_cache",
            required=true,
            formItemTypeForEdit="TEXT",
            formItemTypeForAdd="TEXT_BOX")
    String getDefaultCache();
    void setDefaultCache(String defaultCache);
    
    @Binding(detypedName="eviction-executor")
    @FormItem(localLabel="subsys_infinispan_evictionExecutor",
            required=false,
            formItemTypeForEdit="TEXT_BOX",
            formItemTypeForAdd="TEXT_BOX")
    String getEvictionExecutor();
    void setEvictionExecutor(String evictionExecutor);
    
    @Binding(detypedName="replication-queue-executor")
    @FormItem(localLabel="subsys_infinispan_replicationQueueExecutor",
            required=false,
            formItemTypeForEdit="TEXT_BOX",
            formItemTypeForAdd="TEXT_BOX")
    String getReplicationQueueExecutor();
    void setReplicationQueueExecutor(String replicationQueueExecutor);
    
    @Binding(detypedName="listener-executor")
    @FormItem(localLabel="subsys_infinispan_listenerExecutor",
            required=false,
            formItemTypeForEdit="TEXT_BOX",
            formItemTypeForAdd="TEXT_BOX")
    String getListenerExecutor();
    void setListenerExecutor(String listenerExecutor);
    
    // Transport tab
    @Binding(detypedName="transport/stack")
    @FormItem(label="Stack",
            required=false,
            formItemTypeForEdit="TEXT_BOX",
            formItemTypeForAdd="TEXT_BOX",
            tabName="subsys_infinispan_transport")
    String getStack();
    void setStack(String stack);
    
    @Binding(detypedName="transport/executor")
    @FormItem(label="Executor",
            required=false,
            formItemTypeForEdit="TEXT_BOX",
            formItemTypeForAdd="TEXT_BOX",
            tabName="subsys_infinispan_transport")
    String getExecutor();
    void setExecutor(String executor);
    
    @Binding(detypedName="transport/site")
    @FormItem(label="Site",
            required=false,
            formItemTypeForEdit="TEXT_BOX",
            formItemTypeForAdd="TEXT_BOX",
            tabName="subsys_infinispan_transport")
    String getSite();
    void setSite(String site);
    
    @Binding(detypedName="transport/lock-timeout")
    @FormItem(defaultValue = "60000",
            label="Lock Timeout",
            required=false,
            formItemTypeForEdit="NUMBER_BOX",
            formItemTypeForAdd="NUMBER_BOX",
            tabName="subsys_infinispan_transport")
    Long getLockTimeout();
    void setLockTimeout(Long lockTimeout);
    
    @Binding(detypedName="transport/rack")
    @FormItem(label="Rack",
            required=false,
            formItemTypeForEdit="TEXT_BOX",
            formItemTypeForAdd="TEXT_BOX",
            tabName="subsys_infinispan_transport")
    String getRack();
    void setRack(String rack);
    
    @Binding(detypedName="transport/machine")
    @FormItem(label="Machine",
            required=false,
            formItemTypeForEdit="TEXT_BOX",
            formItemTypeForAdd="TEXT_BOX",
            tabName="subsys_infinispan_transport")
    String getMachine();
    void setMachine(String machine);
    
    @Binding(detypedName="alias", 
             listType="java.lang.String")
    @FormItem(defaultValue="",
             label="Aliases",
             required=false,
             formItemTypeForEdit="UNLIMITED_STRING_LIST_EDITOR",
             formItemTypeForAdd="UNLIMITED_STRING_LIST_EDITOR",
             tabName="subsys_infinispan_aliases")
    public List<String> getAliases();
    public void setAliases(List<String> aliases);
}
