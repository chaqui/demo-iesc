package com.demo.pagos.dto;

import com.demo.pagos.models.TypeInspection;

import lombok.Getter;
import lombok.Setter;

public class DtoTypeInspections {

    @Getter
    @Setter
    public static class Get {
        private Long id;
        private String name;
        private Long cost;

        public Get(TypeInspection typeInspection)
        {
            this.id = typeInspection.getId();
            this.name = typeInspection.getName();
            this.cost = typeInspection.getCost();
        }
    }

}
