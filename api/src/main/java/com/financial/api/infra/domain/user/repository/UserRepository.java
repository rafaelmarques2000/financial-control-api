package com.financial.api.infra.domain.user.repository;

import com.financial.api.domain.user.enums.UserStatus;
import com.financial.api.domain.user.model.User;
import com.financial.api.domain.user.repository.IUserRepository;
import com.financial.api.infra.domain.user.mapper.UserRowMapper;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.UUID;
import java.util.function.BiFunction;

@Component
public class UserRepository implements IUserRepository {

    private DatabaseClient databaseClient;

    public UserRepository(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
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
