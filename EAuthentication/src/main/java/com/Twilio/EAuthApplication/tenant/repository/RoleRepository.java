package com.Twilio.EAuthApplication.tenant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Twilio.EAuthApplication.tenant.model.ERole;
import com.Twilio.EAuthApplication.tenant.model.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
