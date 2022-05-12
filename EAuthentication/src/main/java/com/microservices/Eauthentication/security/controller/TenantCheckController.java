package com.microservices.Eauthentication.security.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.Eauthentication.tenant.model.User;
import com.microservices.Eauthentication.tenant.service.UserService;
import com.microservices.Eauthentication.util.TenantContextHolder;



@RestController
public class TenantCheckController {

	@Autowired
    private UserService userService;

   
    @GetMapping("/tenant/{tenantId}")
    @ResponseBody
    public List<String> getUsersForTenant(
            @PathVariable("tenantId") String tenantId) {
        TenantContextHolder.setTenantId(tenantId);
        List<User> users = userService.findAllUsers();
        List<String> userList = users.stream().map(result -> result.toString())
                .collect(Collectors.toList());
        return userList;
    }
    @GetMapping("/tencheck")
    @ResponseBody
    public List<User> getUsersForTenant2(@RequestHeader("tenantId") String tenantId) {
    	System.out.println("tenant Id ::"+tenantId);
        TenantContextHolder.setTenantId(tenantId);
        List<User> users = userService.findAllUsers();
        List<String> userList = users.stream().map(result -> result.toString())
                .collect(Collectors.toList());
        return users;
    }
}
