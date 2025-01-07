package com.demo.pagos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.pagos.models.Pay;

@Repository
public interface PayRepository extends JpaRepository<Pay,Long> {

    @Query("SELECT p FROM Pay p WHERE p.client.id = ?1")
    List<Pay> findByClientId(Long clientId);

    @Query("SELECT p FROM Pay p WHERE p.client.id = ?1 AND p.status = ?2")
    List<Pay> findByClientIdAndStatus(Long clientId, Long status);

    @Query("SELECT p FROM Pay p WHERE p.status = ?1")
    List<Pay> findByStatus(Long status);

}
