package com.financial.api.app.controllers;

import com.financial.api.app.mappers.TransactionTypeMapper;
import com.financial.api.app.responses.TransactionTypeResponse;
import com.financial.api.domain.transaction.service.ITransactionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/transaction-types")
public class TransactionTypeController {

    @Autowired
    private ITransactionTypeService transactionTypeService;

    @GetMapping
    public Mono<ResponseEntity<?>> getAll() {
        List<TransactionTypeResponse> responseList = new ArrayList<>();
        return transactionTypeService.findAll()
                .map(transactionType -> responseList.add(TransactionTypeMapper.fromTransactionTypeToTransactionTypeResponse(transactionType)))
                .then().thenReturn(ResponseEntity.ok().body(responseList));
    }
}
