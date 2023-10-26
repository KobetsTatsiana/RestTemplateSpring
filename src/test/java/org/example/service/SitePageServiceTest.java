package org.example.service;

import org.example.model.Advertising;
import org.example.model.SitePage;
import org.example.repository.AdvertisingRepository;
import org.example.repository.SitePageRepository;
import org.example.service.dto.AdvertisingServiсeDto;
import org.example.service.dto.SitePageServiceDto;
import org.example.service.dto.mapper.AdvertisingMapper;
import org.example.service.dto.mapper.SitePageMapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SitePageServiceTest {
    @InjectMocks
    private SitePageService sitePageService;
    @Mock
    private SitePageRepository sitePageRepository;
    @Mock
    private AdvertisingRepository advertisingRepository;
    @Mock
    private SitePageMapper sitePageMapper;
    @Mock
    private AdvertisingMapper advertisingMapper;

    private Long testId;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        testId = 1L;
    }

    @Test
    void createOrUpdatePageNewPageTest() {
        SitePageServiceDto sitePageServiceDto = new SitePageServiceDto();
        sitePageServiceDto.setAdvertisingList(new ArrayList<>());
        AdvertisingServiсeDto advertisingServiсeDto = new AdvertisingServiсeDto();

        SitePage sitePage = new SitePage();
        Advertising advertising = new Advertising();

        when(advertisingMapper.toEntity(advertisingServiсeDto)).thenReturn(advertising);
        when(sitePageMapper.toEntity(sitePageServiceDto)).thenReturn(sitePage);
        when(advertisingRepository.save(advertising)).thenReturn(advertising);
        when(sitePageRepository.save(sitePage)).thenReturn(sitePage);
        when(sitePageMapper.toDTO(sitePage)).thenReturn(sitePageServiceDto);

        SitePageServiceDto result = sitePageService.createOrUpdatePage(sitePageServiceDto);

        assertNotNull(result);
    }

    @Test
    void getAllPagesTest() {
        SitePage sitePage = new SitePage();
        List<SitePage> sitePageList = Collections.singletonList(sitePage);

        when(sitePageRepository.findAll()).thenReturn(sitePageList);
        when(sitePageMapper.toDTOList(sitePageList)).thenReturn(Collections.singletonList(new SitePageServiceDto()));

        List<SitePageServiceDto> results = sitePageService.getAllPages();

        assertFalse(results.isEmpty());
    }

    @Test
    void getPageByIdTest() {
        SitePage sitePage = new SitePage();

        when(sitePageRepository.findById(testId)).thenReturn(Optional.of(sitePage));
        when(sitePageMapper.toDTO(sitePage)).thenReturn(new SitePageServiceDto());

        SitePageServiceDto result = sitePageService.getPageById(testId);

        assertNotNull(result);
    }

    @Test
    void getPageByIdNotFoundTest() {
        when(sitePageRepository.findById(testId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> sitePageService.getPageById(testId));
    }

    @Test
    void deletePageTest() {
        doNothing().when(sitePageRepository).deleteById(testId);

        sitePageService.deletePage(testId);

        verify(sitePageRepository, times(1)).deleteById(testId);
    }

    @Test
    void createOrUpdatePageNewAdvertisingWithNullIdTest() {
        SitePageServiceDto sitePageServiceDto = new SitePageServiceDto();
        AdvertisingServiсeDto advertisingServiсeDto = new AdvertisingServiсeDto();
        sitePageServiceDto.setAdvertisingList(Collections.singletonList(advertisingServiсeDto));

        Advertising advertising = new Advertising();
        SitePage sitePage = new SitePage();

        when(advertisingMapper.toEntity(advertisingServiсeDto)).thenReturn(advertising);
        when(advertisingRepository.save(advertising)).thenReturn(advertising);
        when(sitePageMapper.toEntity(sitePageServiceDto)).thenReturn(sitePage);

        sitePageService.createOrUpdatePage(sitePageServiceDto);

        verify(advertisingMapper).toEntity(advertisingServiсeDto);
        verify(advertisingRepository).save(advertising);
    }

    @Test
    void createOrUpdatePageExistingAdvertisingWithIdTest() {
        SitePageServiceDto sitePageServiceDto = new SitePageServiceDto();
        AdvertisingServiсeDto advertisingServiсeDto = new AdvertisingServiсeDto();
        advertisingServiсeDto.setId(testId);
        advertisingServiсeDto.setInfoText("Updated InfoText");
        sitePageServiceDto.setAdvertisingList(Collections.singletonList(advertisingServiсeDto));

        Advertising existingAdvertising = new Advertising();
        SitePage sitePage = new SitePage();

        when(advertisingRepository.findById(testId)).thenReturn(Optional.of(existingAdvertising));
        when(advertisingRepository.save(existingAdvertising)).thenReturn(existingAdvertising);
        when(sitePageMapper.toEntity(sitePageServiceDto)).thenReturn(sitePage);

        sitePageService.createOrUpdatePage(sitePageServiceDto);

        assertEquals("Updated InfoText", existingAdvertising.getInfoText());
        verify(advertisingRepository).save(existingAdvertising);
    }

    @Test
    void createOrUpdatePageNonExistingAdvertisingWithIdTest() {
        SitePageServiceDto sitePageServiceDto = new SitePageServiceDto();
        AdvertisingServiсeDto advertisingServiсeDto = new AdvertisingServiсeDto();
        advertisingServiсeDto.setId(testId);
        sitePageServiceDto.setAdvertisingList(Collections.singletonList(advertisingServiсeDto));

        Advertising advertising = new Advertising();
        SitePage sitePage = new SitePage();

        when(advertisingRepository.findById(testId)).thenReturn(Optional.empty());
        when(advertisingMapper.toEntity(advertisingServiсeDto)).thenReturn(advertising);
        when(advertisingRepository.save(advertising)).thenReturn(advertising);
        when(sitePageMapper.toEntity(sitePageServiceDto)).thenReturn(sitePage);

        sitePageService.createOrUpdatePage(sitePageServiceDto);

        verify(advertisingMapper).toEntity(advertisingServiсeDto);
        verify(advertisingRepository).save(advertising);
    }

    @AfterEach
    public void cleanUp() {
        reset(sitePageRepository, advertisingRepository, sitePageMapper, advertisingMapper);
    }
}

