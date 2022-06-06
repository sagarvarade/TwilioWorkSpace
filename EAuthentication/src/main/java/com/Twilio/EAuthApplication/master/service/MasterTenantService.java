
package com.Twilio.EAuthApplication.master.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.Twilio.EAuthApplication.master.model.MasterTenant;


/**
 * Service definition which accesses the {@link MasterTenant} entity. This is
 * the recommended way to access the entities through an interface rather than
 * using the corresponding repository. This allows for separation into
 * repository code and the service layer.
 *
 */
public interface MasterTenantService {
    
    MasterTenant findByTenantIdAndMicroserviceName(@Param("tenantId") String tenantId,@Param("microserviceName") String microserviceName);

    List<MasterTenant> findByMicroserviceName(@Param("microserviceName") String microserviceName);
}
