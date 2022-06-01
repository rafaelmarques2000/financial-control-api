package com.financial.api.infra.repositories.user;

import com.financial.api.domain.user.enums.UserStatus;
import com.financial.api.domain.user.model.User;
import com.financial.api.domain.user.repository.IUserRepository;
import com.financial.api.infra.repositories.AbstractRepository;
import com.financial.api.infra.repositories.user.mapper.UserRowMapper;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Mono;

@Component
public class UserRepository extends AbstractRepository implements IUserRepository {

    public UserRepository(DatabaseClient databaseClient, TransactionalOperator operator) {
        super(databaseClient, operator);
    }

    @Override
    public Mono<User> findByUsernameAndPassword(String username, String password) {
         return this.databaseClient.sql("SELECT * from cx_user WHERE login=:user AND password = :pass AND status=:status")
                 .bind("user", username)
                 .bind("pass", password)
                 .bind("status", UserStatus.ACTIVE)
                 .map(UserRowMapper.toUser()).one();
    }

    @Override
    public Mono<User> findById(String id) {
        return this.databaseClient.sql("SELECT * from cx_user WHERE id::text=:idUser")
                .bind("idUser", id)
                .map(UserRowMapper.toUser()).one();
    }


}
