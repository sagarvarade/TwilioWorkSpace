package com.microservices.Eauthentication.tenant.config;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.sql.DataSource;

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.microservices.Eauthentication.master.model.MasterTenant;
import com.microservices.Eauthentication.master.repository.MasterTenantRepository;
import com.microservices.Eauthentication.util.DataSourceUtil;
import com.microservices.Eauthentication.util.TenantContextHolder;




/**
 * This class does the job of selecting the correct database based on the tenant id found by the
 * {@link CurrentTenantIdentifierResolverImpl}
 * 
 *
 */
@Configuration
public class DataSourceBasedMultiTenantConnectionProviderImpl
        extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

    private static final Logger LOG = LoggerFactory.getLogger(DataSourceBasedMultiTenantConnectionProviderImpl.class);

    private static final long serialVersionUID = 1L;

    /**
     * Injected MasterTenantRepository to access the tenant information from the master_tenant table
     */
    @Autowired
    private MasterTenantRepository masterTenantRepo;

    /**
     * Map to store the tenant ids as key and the data source as the value
     */
    private Map<String, DataSource> dataSourcesMtApp = new TreeMap<>();

    @Override
    protected DataSource selectAnyDataSource() {
        // This method is called more than once. So check if the data source map
        // is empty. If it is then rescan master_tenant table for all tenant
        // entries.
        if (dataSourcesMtApp.isEmpty()) {
            List<MasterTenant> masterTenants = masterTenantRepo.findAll();
            LOG.info("11 >>>> selectAnyDataSource() -- Total tenants:" + masterTenants.size());
            for (MasterTenant masterTenant : masterTenants) {
                dataSourcesMtApp.put(masterTenant.getTenantId(),
                        DataSourceUtil.createAndConfigureDataSource(masterTenant));
            }
        }
        return this.dataSourcesMtApp.values().iterator().next();
    }

    @Override
    protected DataSource selectDataSource(String tenantIdentifier) {
        // If the requested tenant id is not present check for it in the master
        // database 'master_tenant' table

    	System.out.println("Tenant Identifier :"+tenantIdentifier);
        tenantIdentifier = initializeTenantIfLost(tenantIdentifier);

        if (!this.dataSourcesMtApp.containsKey(tenantIdentifier)) {
        	
            List<MasterTenant> masterTenants = masterTenantRepo.findAll();
            System.out.println("mastertenat "+masterTenants.toString());
            System.out.println("2 mastertenat "+masterTenants.get(0));
            LOG.info(
                    "22 >>>> selectDataSource() -- tenant:" + tenantIdentifier + " Total tenants:" + masterTenants.size());
            for (MasterTenant masterTenant : masterTenants) {
				if (this.dataSourcesMtApp.containsKey(masterTenant.getTenantId())) {
					continue;
				}
                dataSourcesMtApp.put(masterTenant.getTenantId(),
                        DataSourceUtil.createAndConfigureDataSource(masterTenant));
            }
        }
        System.out.println(" Map "+dataSourcesMtApp.entrySet());
        
         //check again if tenant exist in map after rescan master_db, if not, throw UsernameNotFoundException
        if (!this.dataSourcesMtApp.containsKey(tenantIdentifier)) {
            LOG.warn("33 Trying to get tenant:" + tenantIdentifier + " which was not found in master db after rescan");
            throw new ArithmeticException(String.format("111111111Tenant not found after rescan, tenant=%s",tenantIdentifier));
        }
        return this.dataSourcesMtApp.get(tenantIdentifier);
    }

    /**
     * Initialize tenantId based on the logged in user if the tenant Id got lost in after form submission in a user
     * session.
     * 
     * @param tenantIdentifier
     * @return tenantIdentifier
     */
    private String initializeTenantIfLost(String tenantIdentifier) {
       /* if (TenantContextHolder.getTenant() == null) {

            SecurityContext securityContext = SecurityContextHolder.getContext();
            Authentication authentication = securityContext.getAuthentication();
            CustomUserDetails customUserDetails = null;
            if (authentication != null) {
                Object principal = authentication.getPrincipal();
                customUserDetails = principal instanceof CustomUserDetails ? (CustomUserDetails) principal : null;
            }
            TenantContextHolder.setTenantId(customUserDetails.getTenant());
        }*/
    	

        TenantContextHolder.setTenantId(tenantIdentifier);
        if (tenantIdentifier != TenantContextHolder.getTenant()) {
            tenantIdentifier = TenantContextHolder.getTenant();
        }
        return tenantIdentifier;
    }
}
