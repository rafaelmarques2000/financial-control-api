package com.financial.api.domain.transaction.service;

import com.financial.api.domain.transaction.exception.TransactionNotFoundException;
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
        return transactionRepository.save(transaction);
    }

    @Override
    public Mono<Transaction> update(Transaction transaction) {
        return transactionRepository
                .findById(transaction.accountId(),transaction.id())
                .switchIfEmpty(Mono.error(new TransactionNotFoundException("Transaction not found")))
                .then(transactionRepository.update(transaction));
    }

    @Override
    public Mono<Void> delete(String accountId,String transactionId) {
        return transactionRepository
                .findById(accountId,transactionId)
                .switchIfEmpty(Mono.error(new TransactionNotFoundException("Transaction not found")))
                .then(transactionRepository.delete(accountId,transactionId));
    }

    @Override
    public Flux<Transaction> findAll(String accountId) {
        return transactionRepository
                .findAll(accountId);
    }

    @Override
    public Mono<Transaction> findById(String accountId, String transactionId) {
        return transactionRepository
                .findById(accountId, transactionId)
                .switchIfEmpty(Mono.error(new TransactionNotFoundException("Transaction not found")));
    }
}
