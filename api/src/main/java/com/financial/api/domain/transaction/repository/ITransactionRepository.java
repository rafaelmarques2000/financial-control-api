package com.financial.api.domain.transaction.repository;

import com.financial.api.domain.transaction.filter.TransactionFilter;
import com.financial.api.domain.transaction.model.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface ITransactionRepository {
    Mono<Transaction> save(Transaction transaction);
    Mono<Transaction> update(Transaction transaction);
    Mono<Void> delete(String accountId, String transactionId);
    Flux<Transaction> findAll(String accountId, TransactionFilter transactionFilter);
    Mono<Transaction> findById(String accountId,String transactionId);

    Flux<Transaction> findByDateAndServiceReference(LocalDate beginDate, LocalDate endDate, String serviceReference);
}
