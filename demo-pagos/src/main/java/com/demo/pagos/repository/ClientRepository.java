package com.demo.pagos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.pagos.models.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {


}
