package com.financial.api.app.controllers;

import com.financial.api.app.mappers.TransactionCategoryMapper;
import com.financial.api.app.responses.TransactionCategoryResponse;
import com.financial.api.domain.transaction.model.Transaction;
import com.financial.api.domain.transaction.service.ITransactionCategoryService;
import com.financial.api.domain.transaction.service.ITransactionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/transaction-types/{typeId}/categories")
public class TransactionCategoryController {

    @Autowired
    private ITransactionCategoryService transactionCategoryService;

    @GetMapping
    public Mono<ResponseEntity<?>> getAll(@PathVariable String typeId) {
        List<TransactionCategoryResponse> responseList = new ArrayList<>();
        return transactionCategoryService.findAll(typeId).
                map(category -> responseList.add(TransactionCategoryMapper.fromTransactionCategoryToTransactionCategoryResponse(category)))
                .then().thenReturn(ResponseEntity.ok().body(responseList));
    }
}
