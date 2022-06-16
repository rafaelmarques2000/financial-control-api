package com.financial.api.domain.transaction.repository;

import com.financial.api.domain.transaction.model.TransactionType;
import reactor.core.publisher.Flux;

import java.util.Optional;

public interface ITransactionTypeRepository {
    Flux<TransactionType> findAll();
    Optional<TransactionType> noReactiveFindBySlugname(String slugname);
}
