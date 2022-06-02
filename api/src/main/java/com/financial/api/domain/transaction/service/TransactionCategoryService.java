package com.financial.api.domain.transaction.service;

import com.financial.api.domain.transaction.model.TransactionCategory;
import com.financial.api.domain.transaction.model.TransactionType;
import com.financial.api.domain.transaction.repository.ITransactionCategoryRepository;
import com.financial.api.domain.transaction.repository.ITransactionTypeRepository;
import reactor.core.publisher.Flux;

public class TransactionCategoryService implements ITransactionCategoryService{

    private ITransactionCategoryRepository transactionCategoryRepository;

    public TransactionCategoryService(ITransactionCategoryRepository transactionCategoryRepository) {
        this.transactionCategoryRepository = transactionCategoryRepository;
    }

    @Override
    public Flux<TransactionCategory> findAll(String transactionTypeId) {
        return transactionCategoryRepository.findAll(transactionTypeId);
    }
}
