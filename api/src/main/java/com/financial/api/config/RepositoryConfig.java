package com.financial.api.config;

import com.financial.api.domain.account.repository.IAccountRepository;
import com.financial.api.domain.accountUser.repository.IAccountUserRepository;
import com.financial.api.domain.transaction.repository.ITransactionCategoryRepository;
import com.financial.api.domain.transaction.repository.ITransactionRepository;
import com.financial.api.domain.transaction.repository.ITransactionTypeRepository;
import com.financial.api.domain.user.repository.IUserRepository;
import com.financial.api.infra.repositories.account.AccountRepository;
import com.financial.api.infra.repositories.accountUser.AccountUserRepository;
import com.financial.api.infra.repositories.transaction.TransactionCategoryRepository;
import com.financial.api.infra.repositories.transaction.TransactionRepository;
import com.financial.api.infra.repositories.transaction.TransactionTypeRepository;
import com.financial.api.infra.repositories.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.transaction.reactive.TransactionalOperator;

@Configuration
public class RepositoryConfig {

    @Bean
    public IUserRepository userRepository(DatabaseClient databaseClient, TransactionalOperator transactionalOperator) {
        return new UserRepository(databaseClient, transactionalOperator);
    }

    @Bean
    public IAccountRepository accountRepository(DatabaseClient databaseClient, TransactionalOperator transactionalOperator) {
        return new AccountRepository(databaseClient, transactionalOperator);
    }

    @Bean
    public IAccountUserRepository accountUserRepository(DatabaseClient databaseClient, TransactionalOperator transactionalOperator) {
        return new AccountUserRepository(databaseClient, transactionalOperator);
    }

    @Bean
    public ITransactionRepository transactionRepository(DatabaseClient databaseClient, TransactionalOperator transactionalOperator) {
        return new TransactionRepository(databaseClient, transactionalOperator);
    }

    @Bean
    public ITransactionTypeRepository transactionTypeRepository(DatabaseClient databaseClient, TransactionalOperator transactionalOperator) {
        return new TransactionTypeRepository(databaseClient, transactionalOperator);
    }

    @Bean
    public ITransactionCategoryRepository transactionCategoryRepository(DatabaseClient databaseClient, TransactionalOperator transactionalOperator) {
        return new TransactionCategoryRepository(databaseClient, transactionalOperator);
    }
}
