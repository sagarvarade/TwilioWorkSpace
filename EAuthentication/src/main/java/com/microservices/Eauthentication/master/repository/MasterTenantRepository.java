/*
 * Copyright 2018 onwards - Sunit Katkar (sunitkatkar@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.microservices.Eauthentication.master.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.microservices.Eauthentication.master.model.MasterTenant;



/**
 * Repository for the {@link MasterTenant} JPA entity. Any custom methods, not
 * already defined in {@link JpaRepository}, are to be defined here
 * 
 */
@Repository
public interface MasterTenantRepository
        extends JpaRepository<MasterTenant, Long> {

    /**
     * Using a custom named query
     * @param tenantId
     * @return
     */
    @Query("select p from MasterTenant p where p.tenantId = :tenantId and p.microserviceName=:microserviceName")
    MasterTenant findByTenantIdAndMicroserviceName(@Param("tenantId") String tenantId,@Param("microserviceName") String microserviceName);

    @Query("select p from MasterTenant p where p.microserviceName=:microserviceName")
	List<MasterTenant> findByMicroserviceName(String microserviceName);
}
