package com.financial.api.app.controllers;

import com.financial.api.app.mappers.TransactionMapper;
import com.financial.api.app.responses.TransactionResponse;
import com.financial.api.domain.transaction.filter.TransactionFilter;
import com.financial.api.domain.transaction.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/users/{userId}/accounts/transactions")
public class TransactionNoAccountFilterController {

    @Autowired
    ITransactionService transactionService;

    @GetMapping
    public Mono<ResponseEntity<?>> getAll(
            @PathVariable String userId,
            @RequestParam(name = "beginDate", required = false)String beginDate,
            @RequestParam(name = "endDate", required = false) String endDate,
            @RequestParam(name = "categoryId", required = false) String categoryId,
            @RequestParam(name = "typeId", required = false) String typeId
    ) {

        List<TransactionResponse> responseList = new ArrayList<>();
        return transactionService
                .findAll(userId,null, new TransactionFilter(beginDate, endDate, categoryId, typeId))
                .map(transaction -> responseList.add(TransactionMapper.fromTransactionToTransactionResponse(transaction)))
                .then().thenReturn(ResponseEntity.ok().body(responseList));
    }
}
