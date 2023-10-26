package org.example.service.dto.mapper;

import org.example.model.UserEntity;
import org.example.model.UserSite;
import org.example.service.dto.UserEntityServiceDto;
import org.example.service.dto.UserSiteServiceDto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class UserEntityMapperTest {

    @Mock
    private UserSiteMapper userSiteMapper;

    @InjectMocks
    private UserEntityMapperImpl userEntityMapper;

    @BeforeEach
    public void setup() {
        lenient().when(userSiteMapper.toDTO(any())).thenReturn(new UserSiteServiceDto());
        lenient().when(userSiteMapper.toEntity(any())).thenReturn(new UserSite());
    }

    @Test
    void testToDTO() {
        UserEntity userEntity = new UserEntity();
        UserEntityServiceDto result = userEntityMapper.toDTO(userEntity);
    }

    @Test
    void testToEntity() {
        UserEntityServiceDto userEntityServiceDto = new UserEntityServiceDto();
        UserEntity result = userEntityMapper.toEntity(userEntityServiceDto);
    }

    @Test
    void testToDTOList() {
        List<UserEntity> userEntityList = new ArrayList<>();
        List<UserEntityServiceDto> result = userEntityMapper.toDTOList(userEntityList);
    }

    @Test
    void testToEntityList() {
        List<UserEntityServiceDto> userEntityServiceDtoArrayList = new ArrayList<>();
        List<UserEntity> result = userEntityMapper.toEntityList(userEntityServiceDtoArrayList);
    }
}
