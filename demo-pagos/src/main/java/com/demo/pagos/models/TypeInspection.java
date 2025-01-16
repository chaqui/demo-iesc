package com.demo.pagos.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "type_inspections")
@NoArgsConstructor
public class TypeInspection {

    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private Long cost;

}
