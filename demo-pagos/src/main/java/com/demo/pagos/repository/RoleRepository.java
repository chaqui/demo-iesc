package com.demo.pagos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.pagos.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

}
