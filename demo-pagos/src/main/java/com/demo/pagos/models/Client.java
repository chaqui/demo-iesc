package com.demo.pagos.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "clients")
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_id_client")
    @SequenceGenerator(name = "sequence_id_client", sequenceName = "sequence_id_client", allocationSize = 1)
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String phone;

    @Column
    private String address;
}
