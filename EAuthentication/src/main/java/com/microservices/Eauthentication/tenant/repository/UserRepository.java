
package com.microservices.Eauthentication.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.microservices.Eauthentication.tenant.model.User;


/**
 * Repository for the {@link User} JPA entity. Any custom methods, not already
 * defined in {@link JpaRepository}, are to be defined here
 * 
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Custom / Named query for selecting a user based on the username and
     * tenant id
     * 
     * @param username
     * @param tenant
     * @return
     */
    @Query("select p from User p where p.username = :username and p.tenant = :tenant")
    User findByUsernameAndTenantname(@Param("username") String username,
            @Param("tenant") String tenant);
}
