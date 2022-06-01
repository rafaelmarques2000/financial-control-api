package com.financial.api.config;

import com.financial.api.domain.account.repository.IAccountRepository;
import com.financial.api.domain.account.service.AccountService;
import com.financial.api.domain.account.service.IAccountService;
import com.financial.api.domain.accountUser.repository.IAccountUserRepository;
import com.financial.api.domain.user.repository.IUserRepository;
import com.financial.api.domain.user.service.IUserService;
import com.financial.api.domain.user.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean
    public IUserService userService(IUserRepository userRepository) {
        return new UserService(userRepository);
    }

    @Bean
    public IAccountService accountService(IAccountRepository accountRepository, IUserService userService, IAccountUserRepository accountUserRepository) {
        return new AccountService(accountRepository, userService, accountUserRepository);
    }
}
