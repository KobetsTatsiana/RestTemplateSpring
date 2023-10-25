package org.example.service;

import org.example.model.UserEntity;
import org.example.model.UserSite;
import org.example.repository.UserEntityRepository;
import org.example.service.dto.UserEntityServiceDto;
import org.example.service.dto.mapper.UserEntityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserEntityServiceTest {

    @InjectMocks
    private UserEntityService userEntityService;

    @Mock
    private UserEntityRepository userEntityRepository;

    @Mock
    private UserEntityMapper userEntityMapper;

    private Long testId;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        testId = 1L;
    }

    @Test
    void testGetUserById() {
        UserEntity mockUserEntity = new UserEntity();
        UserEntityServiceDto mockUserEntityServiceDto = new UserEntityServiceDto();

        when(userEntityRepository.findByIdWithSites(any(Long.class))).thenReturn(Optional.of(mockUserEntity));
        when(userEntityMapper.toDTO(mockUserEntity)).thenReturn(mockUserEntityServiceDto);

        UserEntityServiceDto result = userEntityService.getUserById(testId);

        assertEquals(mockUserEntityServiceDto, result);
    }

    @Test
    void testGetAllUsers() {
        UserEntity mockUserEntity = new UserEntity();
        UserEntityServiceDto mockUserEntityServiceDto = new UserEntityServiceDto();

        when(userEntityRepository.findAllWithSites()).thenReturn(Collections.singletonList(mockUserEntity));
        when(userEntityMapper.toDTO(mockUserEntity)).thenReturn(mockUserEntityServiceDto);

        List<UserEntityServiceDto> results = userEntityService.getAllUsers();
        assertFalse(results.isEmpty());
        assertEquals(mockUserEntityServiceDto, results.get(0));
    }

    @Test
    void testSaveUserNewUser() {
        UserEntityServiceDto mockUserEntityServiceDto = new UserEntityServiceDto();
        UserEntity mockUserEntity = new UserEntity();
        when(userEntityMapper.toEntity(mockUserEntityServiceDto)).thenReturn(mockUserEntity);
        when(userEntityRepository.existsById(any())).thenReturn(false);
        when(userEntityRepository.save(mockUserEntity)).thenReturn(mockUserEntity);
        when(userEntityMapper.toDTO(mockUserEntity)).thenReturn(mockUserEntityServiceDto);

        UserEntityServiceDto result = userEntityService.saveUser(mockUserEntityServiceDto);

        assertEquals(mockUserEntityServiceDto, result);
    }

    @Test
    void testSaveUserExistingUserWithSitesUpdate() {
        UserEntityServiceDto mockUserEntityServiceDto = new UserEntityServiceDto();
        UserEntity existingUserEntity = new UserEntity();
        UserSite existingUserSite = new UserSite();
        existingUserSite.setId(1L);
        existingUserEntity.setUserSiteList(Collections.singletonList(existingUserSite));

        UserSite updatingUserSite = new UserSite();
        updatingUserSite.setId(existingUserSite.getId());
        updatingUserSite.setNameSite("NameSite");

        UserEntity updatingUserEntity = new UserEntity();
        updatingUserEntity.setId(testId);
        updatingUserEntity.setName("Updated Name");
        updatingUserEntity.setSurname("Updated Surname");
        updatingUserEntity.setAddress("Updated Address");
        updatingUserEntity.setUserSiteList(Collections.singletonList(updatingUserSite));

        when(userEntityMapper.toEntity(mockUserEntityServiceDto)).thenReturn(updatingUserEntity);
        when(userEntityRepository.existsById(testId)).thenReturn(true);
        when(userEntityRepository.findByIdWithSites(testId)).thenReturn(Optional.of(existingUserEntity));
        when(userEntityRepository.save(existingUserEntity)).thenReturn(existingUserEntity);
        when(userEntityMapper.toDTO(existingUserEntity)).thenReturn(mockUserEntityServiceDto);

        UserEntityServiceDto result = userEntityService.saveUser(mockUserEntityServiceDto);

        assertEquals(mockUserEntityServiceDto, result);
        assertEquals("Updated Name", existingUserEntity.getName());
        assertEquals("Updated Surname", existingUserEntity.getSurname());
        assertEquals("Updated Address", existingUserEntity.getAddress());
        assertEquals("NameSite", existingUserSite.getNameSite());
    }

    @Test
    void testSaveUserNewUserWithSites() {
        UserEntityServiceDto mockUserEntityServiceDto = new UserEntityServiceDto();
        UserSite mockUserSite = new UserSite();
        UserEntity mockUserEntity = new UserEntity();
        mockUserEntity.setUserSiteList(Collections.singletonList(mockUserSite));

        when(userEntityMapper.toEntity(mockUserEntityServiceDto)).thenReturn(mockUserEntity);
        when(userEntityRepository.existsById(any())).thenReturn(false);
        when(userEntityRepository.save(mockUserEntity)).thenReturn(mockUserEntity);
        when(userEntityMapper.toDTO(mockUserEntity)).thenReturn(mockUserEntityServiceDto);

        UserEntityServiceDto result = userEntityService.saveUser(mockUserEntityServiceDto);

        assertEquals(mockUserEntityServiceDto, result);
        assertNotNull(mockUserSite.getUserId());
    }

    @Test
    void testDeleteUserEntity() {
        doNothing().when(userEntityRepository).deleteById(any(Long.class));
        userEntityService.deleteUser(testId);

        verify(userEntityRepository, times(1)).deleteById(testId);
    }
}
