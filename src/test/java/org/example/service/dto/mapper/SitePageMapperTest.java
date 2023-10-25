package org.example.service.dto.mapper;

import org.example.model.Advertising;
import org.example.model.SitePage;
import org.example.service.dto.AdvertisingServiсeDto;
import org.example.service.dto.SitePageServiceDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SitePageMapperTest {

    @Mock
    private AdvertisingMapper advertisingMapper;

    @InjectMocks
    private final SitePageMapper sitePageMapper = Mappers.getMapper(SitePageMapper.class);

    @BeforeEach
    public void setup() {
        lenient().when(advertisingMapper.toDTO(any())).thenReturn(new AdvertisingServiсeDto());
        lenient().when(advertisingMapper.toEntity(any())).thenReturn(new Advertising());
    }

    @Test
    void testToDTO() {
        SitePage sitePage = new SitePage();
        sitePage.setNamePage("Test namePage");
        Long id = 1L;
        sitePage.setId(id);
        sitePage.setAdvertisingList(Collections.singletonList(new Advertising()));

        SitePageServiceDto result = sitePageMapper.toDTO(sitePage);

        assertEquals("Test namePage", result.getNamePage());
        assertEquals(id, result.getId());
        assertNotNull(result.getAdvertisingList());
    }

    @Test
    void testToEntity() {
        SitePageServiceDto sitePageServiceDto = new SitePageServiceDto();
        sitePageServiceDto.setNamePage("Test namePage");
        Long id = 1L;
        sitePageServiceDto.setId(id);

        SitePage result = sitePageMapper.toEntity(sitePageServiceDto);

        assertEquals("Test namePage", result.getNamePage());
        assertEquals(id, result.getId());
    }

    @Test
    void testToDTOList() {
        SitePage sitePage = new SitePage();
        sitePage.setNamePage("Test namePage");
        Long id = 1L;
        sitePage.setId(id);
        sitePage.setAdvertisingList(Collections.singletonList(new Advertising()));

        List<SitePageServiceDto> result = sitePageMapper.toDTOList(Collections.singletonList(sitePage));

        assertEquals(1, result.size());
        SitePageServiceDto dto = result.get(0);
        assertEquals("Test namePage", dto.getNamePage());
        assertEquals(id, dto.getId());
    }

    @Test
    void testToEntityList() {
        SitePageServiceDto sitePageServiceDto = new SitePageServiceDto();
        sitePageServiceDto.setNamePage("Test namePage");
        Long id = 1L;
        sitePageServiceDto.setId(id);

        List<SitePage> result = sitePageMapper.toEntityList(Collections.singletonList(sitePageServiceDto));

        assertEquals(1, result.size());
        SitePage entity = result.get(0);
        assertEquals("Test namePage", entity.getNamePage());
        assertEquals(id, entity.getId());
    }
}
