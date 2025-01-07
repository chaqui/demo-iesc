package com.demo.pagos.models;

import com.demo.pagos.dto.DtoRole;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
public class Role {

    @Id
    private Long id;

    private String name;

    public Role(DtoRole.Post roleDto) {
        this.id = roleDto.getId();
        this.name = roleDto.getName();
    }

}
