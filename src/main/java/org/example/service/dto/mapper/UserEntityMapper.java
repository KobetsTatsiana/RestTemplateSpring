package org.example.service.dto.mapper;

import org.example.model.UserEntity;
import org.example.service.dto.UserEntityServiceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserSiteMapper.class)
public interface UserEntityMapper {
    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "surname", target = "surname"),
            @Mapping(source = "address", target = "address"),
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "userSiteList", target = "userSiteList", qualifiedByName = "toDTOList")
    })
    UserEntityServiceDto toDTO(UserEntity entity);

    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "surname", target = "surname"),
            @Mapping(source = "address", target = "address"),
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "userSiteList", target = "userSiteList", qualifiedByName = "toEntityList")
    })
    UserEntity toEntity(UserEntityServiceDto dto);

    List<UserEntityServiceDto> toDTOList(List<UserEntity> entities);

    List<UserEntity> toEntityList(List<UserEntityServiceDto> dtos);
}
