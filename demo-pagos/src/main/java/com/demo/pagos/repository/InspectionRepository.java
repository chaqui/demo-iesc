package com.demo.pagos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.pagos.models.Inspection;

@Repository
public interface InspectionRepository extends JpaRepository<Inspection,Long> {


}
