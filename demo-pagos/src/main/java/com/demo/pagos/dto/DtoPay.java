package com.demo.pagos.dto;

import com.demo.pagos.constants.State;
import com.demo.pagos.models.Pay;

import lombok.Getter;
import lombok.Setter;

public class DtoPay {

    @Getter
    @Setter
    public static class Post {
        private Long clientId;
        private Long amount;
    }

    @Getter
    @Setter
    public static class Get {
        private Long id;
        private Long clientId;
        private Long amount;
        private String state;

        public Get(Pay pay) {
            this.id = pay.getId();
            this.clientId = pay.getClient().getId();
            this.amount = pay.getAmount();
            this.state = State.getById(pay.getStatus()).name();
        }
    }

}
