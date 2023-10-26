package org.example.service.dto.mapper;

import org.example.model.Advertising;
import org.example.service.dto.AdvertisingServiсeDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdvertisingMapper {
    @Mappings({
            @Mapping(source = "infoText", target = "infoText"),
            @Mapping(source = "id", target = "id"),
            @Mapping(target = "sitePageList", ignore = true)
    })
    AdvertisingServiсeDto toDTO(Advertising entity);

    @Mappings({
            @Mapping(source = "infoText", target = "infoText"),
            @Mapping(source = "id", target = "id"),
    })
    Advertising toEntity(AdvertisingServiсeDto dto);

    List<AdvertisingServiсeDto> toDTOList(List<Advertising> entities);

    List<Advertising> toEntityList(List<AdvertisingServiсeDto> dtos);
}
