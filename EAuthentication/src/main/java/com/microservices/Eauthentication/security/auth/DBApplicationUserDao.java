package com.microservices.Eauthentication.security.auth;

import static com.microservices.Eauthentication.security.ApplicationUserRole.*;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

@Repository("DB")
public class DBApplicationUserDao implements ApplicationUserDao {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DBApplicationUserDao(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers().stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        return Lists.newArrayList(
                new ApplicationUser ("anna",
                         passwordEncoder.encode("pass"),
                         STUDENT.getGrantedAuthorities()),
                new ApplicationUser ("sagar",
                         passwordEncoder.encode("sagar"),
                         ADMIN.getGrantedAuthorities()),
                new ApplicationUser ("varade",
                         passwordEncoder.encode("varade"),
                         ADMINTRAINEE.getGrantedAuthorities())
                );
    }
}
