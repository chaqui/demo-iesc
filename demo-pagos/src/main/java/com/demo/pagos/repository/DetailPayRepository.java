package com.demo.pagos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.pagos.models.DetailPay;

@Repository
public interface DetailPayRepository extends JpaRepository<DetailPay,Long> {

    @Query("SELECT d FROM DetailPay d WHERE d.pay.id = ?1")
    List<DetailPay> findByPayId(Long payId);


}
