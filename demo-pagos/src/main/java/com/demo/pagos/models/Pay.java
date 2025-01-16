package com.demo.pagos.models;

import java.util.Date;

import com.demo.pagos.constants.State;

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
@Table(name = "pays")
@NoArgsConstructor
public class Pay {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_id_pay")
    @SequenceGenerator(name = "sequence_id_pay", sequenceName = "sequence_id_pay", allocationSize = 1)
    private Long id;

    @Column
    private Long amount;

    @Column(name = "pay_date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "status")
    private Long status;

    public Pay(Long amount, Client client) {
        this.amount = amount;
        this.client = client;
        this.date = new Date();
        this.status = State.CREATED.getId();
    }
    

}
