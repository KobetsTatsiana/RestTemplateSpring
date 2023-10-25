package org.example.controllers;

import org.example.service.AdvertisingService;
import org.example.service.dto.AdvertisingServiсeDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdvertisingControllerTest {

    @Mock
    private AdvertisingService advertisingService;

    @InjectMocks
    private AdvertisingController advertisingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAdvertisingById() {
        Long id = 1L;
        AdvertisingServiсeDto dto = new AdvertisingServiсeDto();
        when(advertisingService.getAdvertisingById(id)).thenReturn(dto);

        ResponseEntity<AdvertisingServiсeDto> response = advertisingController.getAdvertisingById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testGetAdvertisingByIdThrowsException() {
        Long id = 1L;
        when(advertisingService.getAdvertisingById(id)).thenThrow(new RuntimeException());

        ResponseEntity<AdvertisingServiсeDto> response = advertisingController.getAdvertisingById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetAllAdvertising() {
        AdvertisingServiсeDto dto = new AdvertisingServiсeDto();
        when(advertisingService.getAllAdvertising()).thenReturn(Collections.singletonList(dto));

        ResponseEntity<List<AdvertisingServiсeDto>> response = advertisingController.getAllAdvertising();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
        assertEquals(dto, response.getBody().get(0));
    }

    @Test
    void testCreateAdvertising() {
        AdvertisingServiсeDto dtoToCreate = new AdvertisingServiсeDto();
        AdvertisingServiсeDto createdDto = new AdvertisingServiсeDto();

        when(advertisingService.saveAdvertising(dtoToCreate)).thenReturn(createdDto);

        ResponseEntity<AdvertisingServiсeDto> response = advertisingController.createAdvertising(dtoToCreate);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdDto, response.getBody());
    }

    @Test
    void testUpdateAdvertising() {
        Long id = 1L;
        AdvertisingServiсeDto dto = new AdvertisingServiсeDto();
        dto.setId(id);
        when(advertisingService.saveAdvertising(dto)).thenReturn(dto);

        ResponseEntity<AdvertisingServiсeDto> response = advertisingController.updateAdvertising(id, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testDeleteAdvertising() {
        Long id = 1L;
        ResponseEntity<Void> response = advertisingController.deleteAdvertising(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(advertisingService, times(1)).deleteAdvertising(id);
    }
}
