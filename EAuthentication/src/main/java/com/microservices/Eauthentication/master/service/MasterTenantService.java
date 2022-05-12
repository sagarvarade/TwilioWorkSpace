
package com.microservices.Eauthentication.master.service;

import org.springframework.data.repository.query.Param;

import com.microservices.Eauthentication.master.model.MasterTenant;


/**
 * Service definition which accesses the {@link MasterTenant} entity. This is
 * the recommended way to access the entities through an interface rather than
 * using the corresponding repository. This allows for separation into
 * repository code and the service layer.
 *
 */
public interface MasterTenantService {
    
    MasterTenant findByTenantId(@Param("tenantId") String tenantId);
}
