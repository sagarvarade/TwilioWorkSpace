package com.microservices.Eauthentication.tenant.model;

/**
 * CustomUserDetails class extends the Spring Security provided
 * {@link org.springframework.security.core.userdetails.User} class for
 * authentication purpose. Do not confuse this with the {@link User} class which
 * is an entity for storing application specific user details like username,
 * password, tenant, etc in the database using the JPA {@literal @}Entity
 * annotation.
 * 
 */
public class CustomUserDetails {

    private static final long serialVersionUID = 1L;

    /**
     * The extra field in the login form is for the tenant name
     */
    private String tenant;

    /**
     * Constructor based on the spring security User class but with an extra
     * argument <code>tenant</code> to store the tenant name submitted by the
     * end user.
     * 
     * @param username
     * @param password
     * @param authorities
     * @param tenant
     */
    public CustomUserDetails() {
        
    }

    // Getters and Setters
    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

}
