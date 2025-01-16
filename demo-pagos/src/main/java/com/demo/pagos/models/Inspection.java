package com.demo.pagos.models;

import java.util.Date;

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
@Table(name = "inspections")
@NoArgsConstructor
public class Inspection {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_id_inspection")
    @SequenceGenerator(name = "sequence_id_inspection", sequenceName = "sequence_id_inspection", allocationSize = 1)
    private Long id;

    @Column(name = "inspection_date")
    private Date date;
    
    @ManyToOne
    @JoinColumn(name = "type_inspection_id")
    private TypeInspection typeInspection;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Inspection( TypeInspection typeInspection, User user, Client client) {
        this.date = new Date();
        this.typeInspection = typeInspection;
        this.user = user;
        this.client = client;
    }


}
