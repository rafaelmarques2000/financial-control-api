package com.financial.api.domain.user.service;

import com.financial.api.domain.user.exceptions.UserNotFoundException;
import com.financial.api.domain.user.filter.UserFilter;
import com.financial.api.domain.user.model.User;
import com.financial.api.domain.user.repository.IUserRepository;
import org.springframework.context.annotation.ComponentScan;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ComponentScan
public class UserService implements IUserService{

    private IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Mono<User> findByUsername(String username) {
        return userRepository
                .findByUsername(username)
                .switchIfEmpty(Mono.error(new UserNotFoundException("Usuário não encontrado.")));
    }

    @Override
    public Mono<User> findById(String id) {
        return userRepository.findById(id)
                .switchIfEmpty(Mono.error(new UserNotFoundException("Usuário com o ID:"+id+ " Não foi encontrado.")));
    }

    @Override
    public Flux<User> findAll(UserFilter userFilter) {
        return userRepository.findAll(userFilter);
    }
}
