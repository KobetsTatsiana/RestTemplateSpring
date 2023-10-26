package org.example.service.dto.mapper;

import org.example.model.UserSite;
import org.example.service.dto.UserSiteServiceDto;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserSiteMapper {
    UserSiteServiceDto toDTO(UserSite entity);

    UserSite toEntity(UserSiteServiceDto dto);

    @Named("toDTOList")
    List<UserSiteServiceDto> toDTOList(List<UserSite> entities);

    @Named("toEntityList")
    List<UserSite> toEntityList(List<UserSiteServiceDto> dtos);
}

