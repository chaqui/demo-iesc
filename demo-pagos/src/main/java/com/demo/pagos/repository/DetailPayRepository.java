package com.demo.pagos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.pagos.models.DetailPay;

@Repository
public interface DetailPayRepository extends JpaRepository<DetailPay,Long> {

}
