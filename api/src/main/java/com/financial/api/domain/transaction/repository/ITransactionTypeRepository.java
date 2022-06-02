package com.financial.api.domain.transaction.repository;

import com.financial.api.domain.transaction.model.TransactionType;
import reactor.core.publisher.Flux;

public interface ITransactionTypeRepository {
    Flux<TransactionType> findAll();
}
