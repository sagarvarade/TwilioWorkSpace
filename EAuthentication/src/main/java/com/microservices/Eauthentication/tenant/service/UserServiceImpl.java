package com.microservices.Eauthentication.tenant.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.Eauthentication.tenant.model.User;
import com.microservices.Eauthentication.tenant.repository.UserRepository;


/**
 * Implementation of the {@link UserService} which accesses the {@link User}
 * entity. This is the recommended way to access the entities through an
 * interface rather than using the corresponding repository. This allows for
 * separation into repository code and the service layer.
 * 
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory
            .getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

   

    @Override
    public User save(User user) {
        // Encrypt the password
        user.setPassword(user.getPassword());
        User justSavedUser = userRepository.save(user);
        LOG.info("User:" + justSavedUser.getUsername() + " saved.");
        return justSavedUser;
    }

    @Override
    public String findLoggedInUsername() {
       /* Object userDetails = SecurityContextHolder.getContext()
                .getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            String username = ((UserDetails) userDetails).getUsername();
            LOG.info("Logged in username:" + username);
            return username;
        }*/
        return null;
    }

    @Override
    public User findByUsernameAndTenantname(String username, String tenant) {
        User user = userRepository.findByUsernameAndTenantname(username,
                tenant);
       System.out.println("User : "+username+" ,tenant :  "+tenant);
       System.out.println("Userd : "+user);
       if (user == null) {
            throw new ArithmeticException(
                    String.format(
                            "Username not found for domain, "
                                    + "username=%s, tenant=%s",
                            username, tenant));
        }
        LOG.info("Found user with username:" + user.getUsername()
                + " from tenant:" + user.getTenant());
        return user;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
