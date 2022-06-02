package com.financial.api.domain.transaction.service;

import com.financial.api.domain.transaction.model.TransactionType;
import reactor.core.publisher.Flux;

public interface ITransactionTypeService {
    Flux<TransactionType> findAll();
}
