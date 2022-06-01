package com.financial.api.domain.transaction.service;

import com.financial.api.domain.transaction.model.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITransactionService {
    Mono<Transaction> save(Transaction transaction);
    Mono<Transaction> update(Transaction transaction);
    Mono<Void> delete(String accountId, String transactionId);
    Flux<Transaction> findAll(String accountId);
    Mono<Transaction> findById(String accountId, String transactionId);
}
