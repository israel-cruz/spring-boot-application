package com.israelcruz.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.israelcruz.demo.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

}
