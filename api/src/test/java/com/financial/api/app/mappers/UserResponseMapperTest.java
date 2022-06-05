package com.financial.api.app.mappers;


import com.financial.api.app.responses.UserResponse;
import com.financial.api.domain.user.enums.UserStatus;
import com.financial.api.domain.user.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserResponseMapperTest {

    @Test
    public void testIfCreateUserResponse() {
        //GIVEN
            String userId = "3ee9030c-53ae-418f-a096-6e01dbd159c0";
            String viewName = "Teste SYS";
            User user = new User(userId, "teste", "teste", viewName, LocalDateTime.now(),LocalDateTime.now(),UserStatus.ACTIVE);
        //WHEN
            UserResponse userResponse = UserResponseMapper.toUserFromUserResponse(user);
        //THEN
        Assertions.assertEquals(userId, userResponse.id());
        Assertions.assertEquals(viewName, userResponse.viewName());
    }
}
