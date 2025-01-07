package com.demo.pagos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.pagos.models.Pay;

@Repository
public interface PayRepository extends JpaRepository<Pay,Long> {

}
