package org.example.controllers;

import org.example.service.SitePageService;
import org.example.service.dto.SitePageServiceDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SitePageControllerTest {

    @Mock
    private SitePageService sitePageService;

    @InjectMocks
    private SitePageController sitePageController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPageById() {
        Long id = 1L;
        SitePageServiceDto dto = new SitePageServiceDto();
        when(sitePageService.getPageById(id)).thenReturn(dto);

        ResponseEntity<SitePageServiceDto> response = sitePageController.getPageById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testGetPageByIdThrowsException() {
        Long id = 1L;
        when(sitePageService.getPageById(id)).thenThrow(new RuntimeException());

        ResponseEntity<SitePageServiceDto> response = sitePageController.getPageById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetAllPages() {
        SitePageServiceDto dto = new SitePageServiceDto();
        when(sitePageService.getAllPages()).thenReturn(Collections.singletonList(dto));

        ResponseEntity<List<SitePageServiceDto>> response = sitePageController.getAllPages();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(dto, response.getBody().get(0));
    }

    @Test
    void testCreatePage() {
        SitePageServiceDto dtoToCreate = new SitePageServiceDto();
        SitePageServiceDto createdDto = new SitePageServiceDto();

        when(sitePageService.createOrUpdatePage(dtoToCreate)).thenReturn(createdDto);

        ResponseEntity<SitePageServiceDto> response = sitePageController.createPage(dtoToCreate);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdDto, response.getBody());
    }

    @Test
    void testUpdatePage() {
        Long id = 1L;
        SitePageServiceDto dto = new SitePageServiceDto();
        dto.setId(id);
        when(sitePageService.createOrUpdatePage(dto)).thenReturn(dto);

        ResponseEntity<SitePageServiceDto> response = sitePageController.updatePage(id, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testDeletePage() {
        Long id = 1L;
        ResponseEntity<Void> response = sitePageController.deletePage(id);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(sitePageService, times(1)).deletePage(id);
    }
}