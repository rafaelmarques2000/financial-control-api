package com.financial.api.domain.user.service;

import com.financial.api.domain.user.exceptions.UserNotFoundException;
import com.financial.api.domain.user.model.User;
import com.financial.api.domain.user.repository.IUserRepository;
import org.springframework.context.annotation.ComponentScan;
import reactor.core.publisher.Mono;

@ComponentScan
public class UserService implements IUserService{

    private IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Mono<String> findByUsernameAndPassword(String username, String password) {
        return userRepository
                .findByUsernameAndPassword(username, password)
                .map(User::id)
                .switchIfEmpty(Mono.error(new UserNotFoundException("Usuário não encontrado.")));
    }

    @Override
    public Mono<User> findById(String id) {
        return userRepository.findById(id)
                .switchIfEmpty(Mono.error(new UserNotFoundException("Usuário com o ID:"+id+ " Não foi encontrado.")));
    }
}
