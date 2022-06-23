package com.financial.api.domain.transaction.repository;

import com.financial.api.domain.transaction.model.TransactionCategory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface ITransactionCategoryRepository {
    Flux<TransactionCategory> findAll(String TransactionTypeId);
    Optional<TransactionCategory> noReactiveFindBySlugname(String slugname);
}
