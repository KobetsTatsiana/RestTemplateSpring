package org.example.controllers;

import org.example.service.UserEntityService;
import org.example.service.dto.UserEntityServiceDto;
import org.example.service.dto.UserSiteServiceDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserEntityControllerTest {

    @Mock
    private UserEntityService service;

    @InjectMocks
    private UserEntityController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserById() {
        Long id = 1L;
        UserEntityServiceDto dto = new UserEntityServiceDto();
        when(service.getUserById(id)).thenReturn(dto);

        ResponseEntity<UserEntityServiceDto> response = controller.getUserById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testGetUserByIdThrowsException() {
        Long id = 1L;
        when(service.getUserById(id)).thenThrow(new RuntimeException());

        ResponseEntity<UserEntityServiceDto> response = controller.getUserById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetSitesByUserId() {
        Long id = 1L;
        UserEntityServiceDto dto = new UserEntityServiceDto();
        dto.setUserSiteList(new ArrayList<>());
        when(service.getUserById(id)).thenReturn(dto);

        ResponseEntity<List<UserSiteServiceDto>> response = controller.getSitesByUserId(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto.getUserSiteList(), response.getBody());
    }

    @Test
    void testGetAllUsers() {
        UserEntityServiceDto dto = new UserEntityServiceDto();
        when(service.getAllUsers()).thenReturn(Collections.singletonList(dto));

        ResponseEntity<List<UserEntityServiceDto>> response = controller.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(dto, response.getBody().get(0));
    }

    @Test
    void testCreateUser() {
        UserEntityServiceDto dtoToCreate = new UserEntityServiceDto();
        UserEntityServiceDto createdDto = new UserEntityServiceDto();

        when(service.saveUser(dtoToCreate)).thenReturn(createdDto);

        ResponseEntity<UserEntityServiceDto> response = controller.createUser(dtoToCreate);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdDto, response.getBody());
    }

    @Test
    void testUpdateUser() {
        Long id = 1L;
        UserEntityServiceDto dto = new UserEntityServiceDto();
        dto.setId(id);
        when(service.saveUser(dto)).thenReturn(dto);

        ResponseEntity<UserEntityServiceDto> response = controller.updateUser(id, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testDeleteUser() {
        Long id = 1L;
        ResponseEntity<Void> response = controller.deleteUser(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(service, times(1)).deleteUser(id);
    }
}
