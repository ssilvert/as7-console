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

import org.jboss.as.console.client.shared.viewframework.NamedEntity;
import org.jboss.as.console.client.widgets.forms.Binding;
import org.jboss.as.console.client.widgets.forms.FormItem;

/**
 *
 * @author Stan Silvert ssilvert@redhat.com (C) 2011 Red Hat Inc.
 */
public interface Cache extends NamedEntity {
    @Override
    @Binding(detypedName="name", key=true)
    @FormItem(defaultValue="",
              label="Name",
              required=true,
              formItemTypeForEdit="TEXT",
              formItemTypeForAdd="TEXT_BOX") 
    public String getName();
    @Override
    public void setName(String name);
    
    @Binding(detypedName="controller-mode")
    @FormItem(defaultValue="LAZY",
            label="Controller Mode",
            required=false,
            formItemTypeForEdit="COMBO_BOX",
            formItemTypeForAdd="COMBO_BOX") // VALUES = "EAGER", "LAZY"
    public String getControllerMode();
    public void setControllerMode(String controllerMode);
    
    @Binding(detypedName="batching")
    @FormItem(defaultValue="false",
            label="Batching",
            required=false,
            formItemTypeForEdit="CHECK_BOX",
            formItemTypeForAdd="CHECK_BOX")
    public Boolean isBatching();
    public void setBatching(Boolean isBatching);
    
    @Binding(detypedName="indexing")
    @FormItem(defaultValue="NONE",
            label="Indexing",
            required=false,
            formItemTypeForEdit="COMBO_BOX",
            formItemTypeForAdd="COMBO_BOX") // VALUES = "NONE", "LOCAL", "ALL"
    public String getIndexing();
    public void setIndexing(String indexing);
    
    // Locking attributes
    @Binding(detypedName="isolation")
    @FormItem(defaultValue="REPEATABLE_READ",
            label="Isolation",
            required=false,
            formItemTypeForEdit="ISOLATION_TYPES",
            formItemTypeForAdd="ISOLATION_TYPES",
            tabName="subsys_infinispan_locking")
    public String getIsolation();
    public void setIsolation(String isolation);
    
    @Binding(detypedName="striping")
    @FormItem(defaultValue="false",
            label="Striping",
            required=false,
            formItemTypeForEdit="CHECK_BOX",
            formItemTypeForAdd="CHECK_BOX",
            tabName="subsys_infinispan_locking")
    public Boolean isStriping();
    public void setStriping(Boolean striping);
    
    @Binding(detypedName="acquire-timeout")
    @FormItem(defaultValue="15000",
            label="Acquire Timeout",
            required=false,
            formItemTypeForEdit="NUMBER_BOX",
            formItemTypeForAdd="NUMBER_BOX",
            tabName="subsys_infinispan_locking")
    public Long getAcquireTimeout();
    public void setAcquireTimeout(Long aquireTimeout);
    
    @Binding(detypedName="concurrency-level")
    @FormItem(defaultValue="1000",
            label="Concurrency Level",
            required=false,
            formItemTypeForEdit="NUMBER_BOX",
            formItemTypeForAdd="NUMBER_BOX",
            tabName="subsys_infinispan_locking")
    public Integer getConcurrencyLevel();
    public void setConcurrencyLevel(Integer concurrencyLevel);
    
    
    // eviction attributes
    @Binding(detypedName="strategy")
    @FormItem(defaultValue="NONE",
            label="Eviction Strategy",
            required=false,
            formItemTypeForEdit="EVICTION_STRATEGY_TYPES",
            formItemTypeForAdd="EVICTION_STRATEGY_TYPES",
            tabName="subsys_infinispan_eviction")
    public String getEvictionStrategy();
    public void setEvictionStrategy(String evictionStrategy);
    
    @Binding(detypedName="max-entries")
    @FormItem(defaultValue="10000",
            label="Max Entries",
            required=false,
            formItemTypeForEdit="NUMBER_BOX",
            formItemTypeForAdd="NUMBER_BOX",
            tabName="subsys_infinispan_eviction")
    public Integer getMaxEntries();
    public void setMaxEntries(Integer maxEntries);
    
    
    // expiration attributes
    @Binding(detypedName="max-idle")
    @FormItem(defaultValue="-1",
            label="Max Idle",
            required=false,
            formItemTypeForEdit="NUMBER_BOX_ALLOW_NEGATIVE",
            formItemTypeForAdd="NUMBER_BOX_ALLOW_NEGATIVE",
            tabName="subsys_infinispan_expiration")
    public Long getMaxIdle();
    public void setMaxIdle(Long maxIdle);
    
    @Binding(detypedName="lifespan")
    @FormItem(defaultValue="-1",
            label="Lifespan",
            required=false,
            formItemTypeForEdit="NUMBER_BOX_ALLOW_NEGATIVE",
            formItemTypeForAdd="NUMBER_BOX_ALLOW_NEGATIVE",
            tabName="subsys_infinispan_expiration")
    public Long getLifespan();
    public void setLifespan(Long lifespan);
    
    @Binding(detypedName="interval")
    @FormItem(defaultValue="5000",
            label="Interval",
            required=false,
            formItemTypeForEdit="NUMBER_BOX_ALLOW_NEGATIVE",
            formItemTypeForAdd="NUMBER_BOX_ALLOW_NEGATIVE",
            tabName="subsys_infinispan_expiration")
    public Long getInterval();
    public void setInterval(Long interval);
    
}
