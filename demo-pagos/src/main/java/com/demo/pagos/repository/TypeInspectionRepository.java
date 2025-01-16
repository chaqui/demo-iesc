package com.demo.pagos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.pagos.models.TypeInspection;

@Repository
public interface TypeInspectionRepository extends JpaRepository<TypeInspection, Long> {

}
