package com.architech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.architech.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
