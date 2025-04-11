package com.maintrot.basya.mappers;

import com.maintrot.basya.dtoes.UserRequest;
import com.maintrot.basya.dtoes.UserResponse;
import com.maintrot.basya.models.User;

public interface UserMapper {
    UserResponse toResponse(User user);
    User toEntity(UserRequest request);
}
