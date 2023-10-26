package org.example.service.dto.mapper;

import org.example.model.SitePage;
import org.example.service.dto.SitePageServiceDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = AdvertisingMapper.class)
public interface SitePageMapper {
    @Mappings({
            @Mapping(source = "namePage", target = "namePage"),
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "advertisingList", target = "advertisingList")
    })
    SitePageServiceDto toDTO(SitePage entity);

    @Mapping(source = "namePage", target = "namePage")
    @Mapping(source = "id", target = "id")
    SitePage toEntity(SitePageServiceDto dto);

    List<SitePageServiceDto> toDTOList(List<SitePage> entities);

    List<SitePage> toEntityList(List<SitePageServiceDto> dtos);
}