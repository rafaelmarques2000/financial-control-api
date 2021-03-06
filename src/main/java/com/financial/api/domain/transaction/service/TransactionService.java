package com.financial.api.domain.transaction.service;

import com.financial.api.domain.transaction.exception.TransactionNotFoundException;
import com.financial.api.domain.transaction.filter.TransactionFilter;
import com.financial.api.domain.transaction.model.Transaction;
import com.financial.api.domain.transaction.repository.ITransactionRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class TransactionService implements ITransactionService{

    private ITransactionRepository transactionRepository;

    public TransactionService(ITransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Mono<Transaction> save(Transaction transaction) {
        return transactionRepository.save(transaction)
                .then(transactionRepository.findById(transaction.accountId(), transaction.id()));
    }

    @Override
    public Mono<Transaction> update(Transaction transaction) {
        return transactionRepository
                .findById(transaction.accountId(),transaction.id())
                .switchIfEmpty(Mono.error(new TransactionNotFoundException("Transaction not found")))
                .then(transactionRepository.update(transaction))
                .then(transactionRepository.findById(transaction.accountId(), transaction.id()));
    }

    @Override
    public Mono<Void> delete(String accountId,String transactionId) {
        return transactionRepository
                .findById(accountId,transactionId)
                .switchIfEmpty(Mono.error(new TransactionNotFoundException("Transaction not found")))
                .then(transactionRepository.delete(accountId,transactionId));
    }

    @Override
    public Flux<Transaction> findAll(String userId, String accountId, TransactionFilter filter) {
        return transactionRepository
                .findAll(userId, accountId, filter);
    }

    @Override
    public Mono<Transaction> findById(String accountId, String transactionId) {
        return transactionRepository
                .findById(accountId, transactionId)
                .switchIfEmpty(Mono.error(new TransactionNotFoundException("Transaction not found")));
    }
}
