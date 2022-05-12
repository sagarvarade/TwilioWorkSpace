
package com.microservices.Eauthentication.tenant.service;

import com.microservices.Eauthentication.tenant.model.Role;

/**
 * Service definition which accesses the {@link Role} entity. This is the
 * recommended way to access the entities through an interface rather than using
 * the corresponding repository. This allows for separation into repository code
 * and the service layer.
 *
 */
public interface RoleService {

    Role findByRole(String roleName);
}
