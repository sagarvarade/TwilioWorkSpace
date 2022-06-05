package com.microservices.Eauthentication.master.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.Eauthentication.master.model.MasterTenant;
import com.microservices.Eauthentication.master.repository.MasterTenantRepository;

/**
 * Implementation of the {@link MasterTenantService} which accesses the
 * {@link MasterTenant} entity. This is the recommended way to access the
 * entities through an interface rather than using the corresponding repository.
 * This allows for separation into repository code and the service layer.
 * 
 */
@Service
public class MasterTenantServiceImpl implements MasterTenantService {

    @Autowired
    MasterTenantRepository masterTenantRepo;
    
    @Override
    public MasterTenant findByTenantIdAndMicroserviceName(String tenantId,String microserviceName) {
        return masterTenantRepo.findByTenantIdAndMicroserviceName(tenantId,microserviceName);
    }
    
    @Override
    public List<MasterTenant> findByMicroserviceName(String microserviceName) {
        return masterTenantRepo.findByMicroserviceName(microserviceName);
    }
}
