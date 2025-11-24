package com.xvlkk.sp.security.mapper;

import com.xvlkk.sp.security.dto.ProfileDTO;
import com.xvlkk.sp.security.model.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProfileMapper {
    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

    ProfileDTO map(Profile profile);

    List<Profile> map(List<ProfileDTO> profileDTOS);
}
