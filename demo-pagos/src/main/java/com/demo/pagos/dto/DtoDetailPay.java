package com.demo.pagos.dto;

import com.demo.pagos.models.DetailPay;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class DtoDetailPay {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Post{
        protected Long payId;
        protected Long inspectionId;

    }

    @Getter
    @Setter
    public static class Get extends Post{
        private String typeInspection;
        private Long cost;
        private Long id;

        public Get(DetailPay detailPay){
            this.id = detailPay.getId();
            this.payId = detailPay.getPay().getId();
            this.inspectionId = detailPay.getInspection().getId();
            this.typeInspection = detailPay.getInspection().getTypeInspection().getName();
            this.cost = detailPay.getInspection().getTypeInspection().getCost();
        }
        
    }


}
