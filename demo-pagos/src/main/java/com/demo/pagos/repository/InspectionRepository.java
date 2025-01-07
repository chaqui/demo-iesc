package com.demo.pagos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.pagos.models.Inspection;

@Repository
public interface InspectionRepository extends JpaRepository<Inspection,Long> {

    @Query("SELECT i FROM Inspection i WHERE i.client.id = ?1")
    List<Inspection> findByClientId(Long clientId);


}
