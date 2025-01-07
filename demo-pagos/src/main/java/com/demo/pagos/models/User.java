package com.demo.pagos.models;

import com.demo.pagos.dto.DtoUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_id_user")
    @SequenceGenerator(name = "sequence_id_user", sequenceName = "sequence_id_user", allocationSize = 1)
    private Long id;

    @Column
    private String username;


    @Column
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public User(DtoUser.Post userDto, Role role) {
        this.username = userDto.getName();
        this.role = role;
    }

}
