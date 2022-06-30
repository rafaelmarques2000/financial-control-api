package com.financial.api.app.controllers;

import com.financial.api.app.mappers.TransactionMapper;
import com.financial.api.app.requests.TransactionRequest;
import com.financial.api.app.responses.SuccessResponse;
import com.financial.api.app.responses.TransactionResponse;
import com.financial.api.domain.transaction.filter.TransactionFilter;
import com.financial.api.domain.transaction.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/users/{userId}/accounts/{accountId}/transactions")
public class TransactionController {

    @Autowired
    private ITransactionService transactionService;

    @PostMapping
    public Mono<ResponseEntity<?>> create(@PathVariable String accountId, @RequestBody @Validated TransactionRequest transaction) {
        return transactionService
                .save(TransactionMapper.fromTransactionRequestToTransaction(transaction, null, accountId))
                .map(createdTransaction -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(new SuccessResponse("Transaction created",TransactionMapper.fromTransactionToTransactionResponse(createdTransaction))));
    }

    @PutMapping(value = "/{transactionId}")
    public Mono<ResponseEntity<?>> update(@PathVariable String accountId, @PathVariable String transactionId,@RequestBody @Validated TransactionRequest transaction) {
        return transactionService
                .update(TransactionMapper.fromTransactionRequestToTransaction(transaction, transactionId, accountId))
                .map(updatedTransaction -> ResponseEntity.ok()
                        .body(new SuccessResponse("Transaction updated",TransactionMapper.fromTransactionToTransactionResponse(updatedTransaction))));
    }

    @DeleteMapping(value = "/{transactionId}")
    public Mono<ResponseEntity<?>> delete(@PathVariable String accountId, @PathVariable String transactionId) {
        return transactionService
                .delete(accountId, transactionId)
                .thenReturn(ResponseEntity.noContent().build());
    }

    @GetMapping(value = "/{transactionId}")
    public Mono<ResponseEntity<?>> getById(@PathVariable String accountId, @PathVariable String transactionId) {
        return transactionService
                .findById(accountId, transactionId)
                .map(transaction -> ResponseEntity.ok().body(TransactionMapper.fromTransactionToTransactionResponse(transaction)));
    }

    @GetMapping
    public Mono<ResponseEntity<?>> getAll(
            @PathVariable String userId,
            @PathVariable String accountId,
            @RequestParam(name = "beginDate", required = false)String beginDate,
            @RequestParam(name = "endDate", required = false) String endDate,
            @RequestParam(name = "categoryId", required = false) String categoryId,
            @RequestParam(name = "typeId", required = false) String typeId
    ) {

        List<TransactionResponse> responseList = new ArrayList<>();
        return transactionService
                .findAll(userId,accountId, new TransactionFilter(beginDate, endDate, categoryId, typeId))
                .map(transaction -> responseList.add(TransactionMapper.fromTransactionToTransactionResponse(transaction)))
                .then().thenReturn(ResponseEntity.ok().body(responseList));
    }
}
