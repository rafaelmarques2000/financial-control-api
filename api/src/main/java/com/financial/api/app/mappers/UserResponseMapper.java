package com.financial.api.app.mappers;

import com.financial.api.app.responses.UserResponse;
import com.financial.api.domain.user.model.User;

public class UserResponseMapper {

    public static UserResponse toUserFromUserResponse(User user) {
         return new UserResponse(user.id(), user.viewName());
    }

}
