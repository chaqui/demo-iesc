package com.demo.pagos.dto;

import com.demo.pagos.models.Inspection;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class DtoInspection {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Post {

        protected Long typeInspectionId;
        protected Long clientId;
    }

    @Getter
    @Setter
    public static class Get extends Post {
        private Long id;
        private String nameTypeInspection;
        private String nameClient;
        private Long cost;

        public Get(Inspection inspection) {
            this.id = inspection.getId();
            this.nameTypeInspection = inspection.getTypeInspection().getName();
            this.clientId = inspection.getClient().getId();
            this.typeInspectionId = inspection.getTypeInspection().getId();
            this.nameClient = inspection.getClient().getName();
            this.cost = inspection.getTypeInspection().getCost();

        }
    }

}
