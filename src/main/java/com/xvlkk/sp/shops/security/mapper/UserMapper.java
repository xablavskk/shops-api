package com.xvlkk.sp.shops.security.mapper;

import com.xvlkk.sp.shops.security.dto.UserDetailsImpl;
import com.xvlkk.sp.shops.security.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTNACE = Mappers.getMapper(UserMapper.class);

    User map(UserDetailsImpl userDetails);

    UserDetailsImpl map(User user);
}
