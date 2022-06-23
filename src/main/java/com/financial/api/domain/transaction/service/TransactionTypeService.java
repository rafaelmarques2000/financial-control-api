package com.financial.api.domain.transaction.service;

import com.financial.api.domain.transaction.model.TransactionType;
import com.financial.api.domain.transaction.repository.ITransactionTypeRepository;
import reactor.core.publisher.Flux;

public class TransactionTypeService implements ITransactionTypeService{

    private ITransactionTypeRepository transactionTypeRepository;

    public TransactionTypeService(ITransactionTypeRepository transactionTypeRepository) {
        this.transactionTypeRepository = transactionTypeRepository;
    }

    @Override
    public Flux<TransactionType> findAll() {
        return transactionTypeRepository.findAll();
    }
}
