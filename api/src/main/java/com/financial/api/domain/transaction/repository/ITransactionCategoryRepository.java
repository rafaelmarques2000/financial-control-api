package com.financial.api.domain.transaction.repository;

import com.financial.api.domain.transaction.model.TransactionCategory;
import reactor.core.publisher.Flux;

public interface ITransactionCategoryRepository {
    Flux<TransactionCategory> findAll(String TransactionTypeId);
}
