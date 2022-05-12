
package com.microservices.Eauthentication.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.Eauthentication.tenant.model.Role;


/**
 * Repository for the {@link Role} JPA entity. Any custom methods, not already
 * defined in {@link JpaRepository}, are to be defined here.
 * 
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Query to find a Role entiry based on the {@link Role} name
     * 
     * @param role
     * @return
     */
    Role findByRole(String role);
}
