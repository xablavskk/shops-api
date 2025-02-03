package com.xvlkk.sp.shops.security.mapper;

import com.xvlkk.sp.shops.security.dto.UserDTO;
import com.xvlkk.sp.shops.security.dto.UserDetailsImpl;
import com.xvlkk.sp.shops.security.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


    UserDTO map(User user);

    User map(UserDTO userDTO);

    User map(UserDetailsImpl userDetails);

    UserDetailsImpl mapDetails(User user);
}
