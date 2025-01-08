package com.demo.pagos.models;

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
@Table(name = "detail_pays")
@NoArgsConstructor
public class DetailPay {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_id_detail_pay")
    @SequenceGenerator(name = "sequence_id_detail_pay", sequenceName = "sequence_id_detail_pay", allocationSize = 1)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "pay_id")
    private Pay pay;

    @ManyToOne
    @JoinColumn(name = "inspection_id")
    private Inspection inspection;

    public DetailPay(Pay pay, Inspection inspection) {
        this.pay = pay;
        this.inspection = inspection;
    }

}
