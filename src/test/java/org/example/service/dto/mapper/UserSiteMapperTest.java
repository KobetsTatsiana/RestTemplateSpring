package org.example.service.dto.mapper;

import org.example.model.UserSite;
import org.example.service.dto.UserSiteServiceDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserSiteMapperTest {

    private UserSiteMapper userSiteMapper;

    @BeforeEach
    public void setUp() {
        userSiteMapper = Mappers.getMapper(UserSiteMapper.class);
    }

    @Test
    void testToDTO() {
        UserSite userSite = new UserSite();
        userSite.setId(1L);
        userSite.setNameSite("Test nameSite");

        UserSiteServiceDto userSiteServiceDto = userSiteMapper.toDTO(userSite);

        assertEquals(userSite.getId(), userSiteServiceDto.getId());
        assertEquals(userSite.getNameSite(), userSiteServiceDto.getNameSite());
    }

    @Test
    void testToEntity() {
        UserSiteServiceDto userSiteServiceDto = new UserSiteServiceDto();
        userSiteServiceDto.setId(1L);
        userSiteServiceDto.setNameSite("Test nameSite");

        UserSite userSite = userSiteMapper.toEntity(userSiteServiceDto);

        assertEquals(userSiteServiceDto.getId(), userSite.getId());
        assertEquals(userSiteServiceDto.getNameSite(), userSite.getNameSite());
    }

    @Test
    void testToDTOList() {
        List<UserSite> userSiteList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            UserSite userSite = new UserSite();
            userSite.setId(1L);
            userSite.setNameSite("Test nameSite " + i);
            userSiteList.add(userSite);
        }

        List<UserSiteServiceDto> userSiteServiceDtoList = userSiteMapper.toDTOList(userSiteList);

        assertEquals(userSiteList.size(), userSiteServiceDtoList.size());

        for (int i = 0; i < userSiteList.size(); i++) {
            UserSite userSite = userSiteList.get(i);
            UserSiteServiceDto userSiteServiceDto = userSiteServiceDtoList.get(i);

            assertEquals(userSite.getId(), userSiteServiceDto.getId());
            assertEquals(userSite.getNameSite(), userSiteServiceDto.getNameSite());
        }
    }

    @Test
    void testToEntityList() {
        List<UserSiteServiceDto> userSiteServiceDtoList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            UserSiteServiceDto userSiteServiceDto = new UserSiteServiceDto();
            userSiteServiceDto.setId(1L);
            userSiteServiceDto.setNameSite("Test nameSite " + i);
            userSiteServiceDtoList.add(userSiteServiceDto);
        }

        List<UserSite> userSiteList = userSiteMapper.toEntityList(userSiteServiceDtoList);

        assertEquals(userSiteServiceDtoList.size(), userSiteList.size());

        for (int i = 0; i < userSiteServiceDtoList.size(); i++) {
            UserSiteServiceDto userSiteServiceDto = userSiteServiceDtoList.get(i);
            UserSite userSite = userSiteList.get(i);

            assertEquals(userSiteServiceDto.getId(), userSite.getId());
            assertEquals(userSiteServiceDto.getNameSite(), userSite.getNameSite());
        }
    }
}