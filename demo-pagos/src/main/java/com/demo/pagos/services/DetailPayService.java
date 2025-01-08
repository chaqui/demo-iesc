package com.demo.pagos.services;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.pagos.dto.DtoDetailPay;
import com.demo.pagos.dto.DtoPay.Get;
import com.demo.pagos.exception.HttpException;
import com.demo.pagos.models.DetailPay;
import com.demo.pagos.models.Inspection;
import com.demo.pagos.repository.DetailPayRepository;

@Service
public class DetailPayService {

    @Autowired
    private PayService payService;

    @Autowired
    private InsepctionService inspectionService;

    @Autowired
    private DetailPayRepository detailPayRepository;

    public DetailPay createDetail(DtoDetailPay.Post detailPay) throws HttpException {

        Inspection inspection =this.inspectionService.getById(detailPay.getInspectionId());
        DetailPay newDetailPay = new DetailPay(payService.getById(detailPay.getPayId()), inspection);
        return detailPayRepository.save(newDetailPay);
    }

    public List<DetailPay> getDetailsByPayId(Long payId) {
        return detailPayRepository.findByPayId(payId);
    }
}
