package com.springbootsecurity.demo.repository;


import com.springbootsecurity.demo.domain.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role,Long> {

}
