package com.financial.api.config;

import com.financial.api.domain.account.repository.IAccountRepository;
import com.financial.api.domain.account.service.AccountService;
import com.financial.api.domain.account.service.IAccountService;
import com.financial.api.domain.accountUser.repository.IAccountUserRepository;
import com.financial.api.domain.user.repository.IUserRepository;
import com.financial.api.domain.user.service.IUserService;
import com.financial.api.domain.user.service.UserService;
import com.financial.api.infra.domain.account.repository.AccountRepository;
import com.financial.api.infra.domain.accountUser.repository.AccountUserRepository;
import com.financial.api.infra.domain.user.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.transaction.reactive.TransactionalOperator;

@Configuration
public class AppBeansConfig {

    @Bean
    public IUserRepository userRepository(DatabaseClient databaseClient) {
        return new UserRepository(databaseClient);
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
    public IUserService userService(IUserRepository userRepository) {
        return new UserService(userRepository);
    }

    @Bean
    public IAccountService accountService(IAccountRepository accountRepository, IUserService userService, IAccountUserRepository accountUserRepository) {
        return new AccountService(accountRepository, userService, accountUserRepository);
    }

}
