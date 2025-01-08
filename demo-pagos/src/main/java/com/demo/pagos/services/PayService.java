package com.demo.pagos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.demo.pagos.constants.State;
import com.demo.pagos.dto.DtoPay;
import com.demo.pagos.exception.HttpException;
import com.demo.pagos.models.Client;
import com.demo.pagos.models.Pay;
import com.demo.pagos.repository.PayRepository;

@Service
public class PayService {

    @Autowired
    private PayRepository payRepository;

    @Autowired
    private ClientService clientService;


    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    public Pay savePay(DtoPay.Post pay) throws HttpException {
        Client client = clientService.getById(pay.getClientId());
        return payRepository.save(new Pay(pay.getAmount(), client));
    }

    public List<Pay> getPays() {
        return payRepository.findAll();
    }

    public List<Pay> getPaysByClientId(Long clientId) {
        return payRepository.findByClientId(clientId);
    }

    /**
     * Method to pay a pay
     * 
     * @param payId pay id
     * @return pay
     * @throws HttpException if pay not found
     */
    public Pay payPay(Long payId) throws HttpException {
        Pay pay = this.getById(payId);
        pay.setStatus(State.PAID.getId());
        return payRepository.save(pay);
    }

    public Pay sentPay(Long payId) throws HttpException {
        Pay pay = this.getById(payId);
        pay.setStatus(State.SEND.getId());
        return payRepository.save(pay);
    }

    public Pay getById(Long id) throws HttpException {
        return payRepository.findById(id).orElseThrow(() -> new HttpException("Pay not found", null));
    }

    public List<Pay> getPaysByClientIdAndStatus(Long clientId, Long status) {
        return payRepository.findByClientIdAndStatus(clientId, status);
    }

    public void sendPays() {
        payRepository.findByStatus(State.PAID.getId()).forEach(pay -> {
            this.messagingTemplate.convertAndSend("/topic/pays", new DtoPay.Get(pay));
        });
    }

}
