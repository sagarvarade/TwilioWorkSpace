package com.in28minutes.microservices.Eauthentication.security.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

public class ApplicationUser implements UserDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7038957849630231306L;
	private int id;
	private final String username;
    private final String password;
    private final Set<? extends GrantedAuthority> grantedAuthorities;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;

    public ApplicationUser (String username,
                            String password,
                            Set<? extends GrantedAuthority> grantedAuthorities,
                            boolean accountNonExpired,
                            boolean accountNonLocked,
                            boolean credentialsNonExpired,
                            boolean enabled) {
        this.username = username;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }

    public ApplicationUser (String username,
                            String password,
                            Set<? extends GrantedAuthority> grantedAuthorities) {
        this(username, password, grantedAuthorities, true, true, true, true);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
