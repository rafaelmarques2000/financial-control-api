package com.financial.api.domain.transaction.service;

import com.financial.api.domain.transaction.model.TransactionCategory;
import reactor.core.publisher.Flux;

public interface ITransactionCategoryService {
    Flux<TransactionCategory> findAll(String TransactionTypeId);
}
