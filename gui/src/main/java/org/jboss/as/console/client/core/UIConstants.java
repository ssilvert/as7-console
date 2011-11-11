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

package org.jboss.as.console.client.core;

import com.google.gwt.i18n.client.Constants;

/**
 * @author Heiko Braun
 * @author David Bosschaert
 * @date 5/2/11
 */
public interface UIConstants extends Constants {
    String common_error_contentStillAssignedToGroup();
    String common_error_unexpectedHttpResponse();
    String common_error_detailsMissing();
    String common_error_failedToDecode();
    String common_error_deploymentFailed();
    String common_error_unknownError();
    String common_error_failure();

    String common_label_addProperty();
    String common_label_hostManagement();
    String common_label_profileManagement();
    String common_label_groupManagement();
    String common_label_runtimeStatus();
    String common_label_serverInstances();
    String common_label_serverGroup();
    String common_label_status();
    String common_label_server();
    String common_label_serverConfig();
    String common_label_serverInstance();
    String common_label_instanceDetails();
    String common_label_virtualMachine();
    String common_label_serverConfigs();
    String common_label_noRecords();
    String common_label_hostConfiguration();
    String common_label_systemProperties();
    String common_label_socketBindingGroups();
    String common_label_virtualMachines();
    String common_label_paths();
    String common_label_path();
    String common_label_serverGroups();
    String common_label_deployments();
    String common_label_manageDeployments();
    String common_label_edit();
    String common_label_save();
    String common_label_delete();
    String common_label_createNewServerConfig();
    String common_label_attributes();
    String common_label_details();
    String common_label_name();
    String common_label_autoStart();
    String common_label_socketBinding();
    String common_label_portOffset();
    String common_label_hosts();
    String common_label_profiles();
    String common_label_properties();
    String common_label_noRecentMessages();
    String common_label_messageDetail();
    String common_label_messages();
    String common_label_newServerGroup();
    String common_label_profile();
    String common_label_add();
    String common_label_value();
    String common_label_key();
    String common_label_option();
    String common_label_basedOn();
    String common_label_cancel();
    String common_label_runtimeName();
    String common_label_verifyDeploymentNames();
    String common_label_settings();
    String common_label_generalConfig();
    String common_label_interfaces();
    String common_label_disable();
    String common_label_enable();
    String common_label_enOrDisable();
    String common_label_subsystems();
    String common_label_areYouSure();
    String common_label_addToGroup();
    String common_label_addToGroups();

    // use the term "delete" instead
    @Deprecated
    String common_label_remove();

    String common_label_domain();
    String common_label_upload();
    String common_label_next();
    String common_label_deploymentSelection();
    String common_label_step();
    String common_label_chooseFile();
    String common_label_chooseServerGroup();
    String common_label_finish();
    String common_label_addContent();
    String common_label_contentRepository();
    String common_label_enabled();
    String common_label_lazyActivation();
    String common_label_changeActivation();
    String common_label_success();
    String common_label_reset();
    
    String common_label_selectedGroups();

    String subsys_jca_dataSources();
    String subsys_jca_dataSourcesXA();
    String subsys_jca_newDataSource();
    String subsys_jca_dataSource();
    String subsys_jca_existingDataSources();

    String subsys_logging_loggers();
    String subsys_logging_handlers();
    String subsys_logging_handler();
    String subsys_logging_addHandler();
    String subsys_logging_removeHandler();
    String subsys_logging_autoFlush();
    String subsys_logging_encoding();
    String subsys_logging_filter();
    String subsys_logging_formatter();
    String subsys_logging_append();
    String subsys_logging_fileRelativeTo();
    String subsys_logging_filePath();
    String subsys_logging_rotateSize();
    String subsys_logging_maxBackupIndex();
    String subsys_logging_target();
    String subsys_logging_type();
    String subsys_logging_logLevel();
    String subsys_logging_overflowAction();
    String subsys_logging_subhandlers();
    String subsys_logging_queueLength();
    String subsys_logging_suffix();
    String subsys_logging_handlerConfigurations();
    String subsys_logging_invalidByteSpec();
    String subsys_logging_className();
    String subsys_logging_module();
    String subsys_logging_handlerProperties();
    String subsys_logging_newHandlerProperty();
    String subsys_logging_useParentHandlers();
    String subsys_logging_category();
    String subsys_logging_consoleHandlers();

    String subsys_deploymentscanner_scanners();
    String subsys_deploymentscanner_relativeTo();
    String subsys_deploymentscanner_scanEnabled();
    String subsys_deploymentscanner_scanInterval();
    String subsys_deploymentscanner_autoDeployZipped();
    String subsys_deploymentscanner_autoDeployExploded();
    String subsys_deploymentscanner_deploymentTimeout();

    String subsys_infinispan_cache_container();
    String subsys_infinispan_cache_containers();
    String subsys_infinispan_default_cache();
    String subsys_infinispan_jndiName();
    String subsys_infinispan_listenerExecutor();
    String subsys_infinispan_evictionExecutor();
    String subsys_infinispan_replicationQueueExecutor();
    String subsys_infinispan_isolation();
    String subsys_infinispan_striping();
    String subsys_infinispan_aquireTimeout();
    String subsys_infinispan_concurrencyLevel();
    String subsys_infinispan_evictionStrategy();

    String subsys_naming_jndiView();
    String subsys_naming_jndiBindings();
    String subsys_naming_selectedURI();

    String subsys_osgi_capabilities();
    String subsys_osgi_capability();
    String subsys_osgi_capabilityAdd();
    String subsys_osgi_capabilityEdit();
    String subsys_osgi_capabilityId();
    String subsys_osgi_capabilityStartLevel();
    String subsys_osgi_configAdmin();
    String subsys_osgi_configAdminAdd();
    String subsys_osgi_configAdminEditPID();
    String subsys_osgi_configAdminHeader();
    String subsys_osgi_configAdminPID();
    String subsys_osgi_configAdminPIDLabel();
    String subsys_osgi_configAdminPIDShort();
    String subsys_osgi_configAdminValueAdd();
    String subsys_osgi_configAdminValuesLabel();
    String subsys_osgi_framework();
    String subsys_osgi_frameworkConfiguration();
    String subsys_osgi_frameworkHeader();
    String subsys_osgi_frameworkProperties();
    String subsys_osgi_frameworkProperty();
    String subsys_osgi_frameworkPropertyAdd();
    String subsys_osgi_properties();
    String subsys_osgi_bundleID();
    String subsys_osgi_bundleSymbolicName();
    String subsys_osgi_bundleVersion();
    String subsys_osgi_bundleState();
    String subsys_osgi_bundles();
    String subsys_osgi();

    String subsys_threads_sizing();

    String subsys_messaging_jms_provider();
    String subsys_messaging_jms_destinations();

    String subsys_jca_dataSource_registered();
    String subsys_jca_dataSource_configurations();

    String subsys_jca_dataSource_xaprop_help();

    String subsys_jca_ra_configurations();

    String subsys_jca_ra_registered();

    String subsys_jca_dataSource_step1();
    String subsys_jca_dataSource_step2();
    String subsys_jca_dataSource_step3();

    String subsys_jca_dataSource_select_driver();

    String subsys_jca_xadataSource_step1();
    String subsys_jca_xadataSource_step2();
    String subsys_jca_xadataSource_step3();
    String subsys_jca_xadataSource_step4();

    String subsys_jca_ra_step1();
    String subsys_jca_ra_step2();

    String common_label_refresh();
    String common_label_clear();
    String common_label_hostVm();
    String common_label_action();
    String common_label_start();
    String common_label_stop();
    String common_label_type();

    String subsys_jca_dataSource_verify();

    String subsys_ejb3_asyncService();
    String subsys_ejb3_beanPoolTimeout();
    String subsys_ejb3_beanPools();
    String subsys_ejb3_container();
    String subsys_ejb3_defaultResourceAdapter();
    String subsys_ejb3_ejbServices();
    String subsys_ejb3_maxPoolSize();
    String subsys_ejb3_messageDrivenBeanPool();
    String subsys_ejb3_remoteService();
    String subsys_ejb3_remoteServiceConnector();
    String subsys_ejb3_services();
    String subsys_ejb3_singletonAccessTimeout();
    String subsys_ejb3_statefulAccessTimeout();
    String subsys_ejb3_statelessSessionBeanPool();
    String subsys_ejb3_threadPool();
    String subsys_ejb3_threadPoolKeepAliveTime();
    String subsys_ejb3_threadPoolMaxThreads();
    String subsys_ejb3_threadPools();
    String subsys_ejb3_timerService();
    String subsys_ejb3_timerServicePath();
    String subsys_ejb3_timerServiceRelativeTo();

    String common_label_serverGroupConfigurations();

    String common_label_host();
}
