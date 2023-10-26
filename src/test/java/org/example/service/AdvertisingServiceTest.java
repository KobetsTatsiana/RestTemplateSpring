package org.example.service;

import org.example.model.Advertising;
import org.example.model.SitePage;
import org.example.repository.AdvertisingRepository;
import org.example.repository.SitePageRepository;
import org.example.service.dto.AdvertisingServiсeDto;
import org.example.service.dto.SitePageServiceDto;
import org.example.service.dto.mapper.AdvertisingMapper;
import org.example.service.dto.mapper.SitePageMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AdvertisingServiceTest {

    @InjectMocks
    private AdvertisingService advertisingService;

    @Mock
    private AdvertisingRepository advertisingRepository;

    @Mock
    private SitePageRepository sitePageRepository;

    @Mock
    private AdvertisingMapper advertisingMapper;

    @Mock
    private SitePageMapper sitePageMapper;

    private Long testId;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        testId = 1L;
    }

    @Test
    void getAdvertisingById() {
        Advertising mockAdvertising = new Advertising();
        AdvertisingServiсeDto mockAdvertisingServiсeDto = new AdvertisingServiсeDto();

        when(advertisingRepository.findById(any(Long.class))).thenReturn(Optional.of(mockAdvertising));
        when(advertisingMapper.toDTO(mockAdvertising)).thenReturn(mockAdvertisingServiсeDto);

        AdvertisingServiсeDto result = advertisingService.getAdvertisingById(testId);

        assertEquals(mockAdvertisingServiсeDto, result);
    }

    @Test
    void saveAdvertisingNewAdvertisingWithoutPages() {
        AdvertisingServiсeDto inputDto = new AdvertisingServiсeDto();
        inputDto.setInfoText("Info text");

        Advertising expectedSavedAdvertising = new Advertising();
        expectedSavedAdvertising.setInfoText("Info text");

        when(advertisingRepository.existsById(any())).thenReturn(false);
        when(advertisingMapper.toEntity(inputDto)).thenReturn(expectedSavedAdvertising);
        when(advertisingRepository.save(expectedSavedAdvertising)).thenReturn(expectedSavedAdvertising);
        when(advertisingMapper.toDTO(expectedSavedAdvertising)).thenReturn(inputDto);

        AdvertisingServiсeDto resultDto = advertisingService.saveAdvertising(inputDto);

        assertEquals(inputDto.getInfoText(), resultDto.getInfoText());
        assertEquals(0, resultDto.getSitePageList().size());
    }

    @Test
    void throwExceptionWhenUpdatingNonExistingAdvertisingTest() {
        AdvertisingServiсeDto mockAdvertisingServiсeDto = new AdvertisingServiсeDto();
        mockAdvertisingServiсeDto.setId(testId);

        when(advertisingRepository.existsById(testId)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> advertisingService.saveAdvertising(mockAdvertisingServiсeDto));
    }

    @Test
    void getAllAdvertisingTest() {
        Advertising mockAdvertising = new Advertising();
        SitePage mockSitePage = new SitePage();

        when(advertisingRepository.findAll()).thenReturn(Collections.singletonList(mockAdvertising));

        AdvertisingServiсeDto mockAdvertisingServiсeDto = new AdvertisingServiсeDto();
        when(advertisingMapper.toDTOList(Collections.singletonList(mockAdvertising))).thenReturn(Collections.singletonList(mockAdvertisingServiсeDto));

        when(sitePageRepository.findByAdvertisingListId(any(Long.class))).thenReturn(Collections.singletonList(mockSitePage));

        SitePageServiceDto mockSitePageServiceDto = new SitePageServiceDto();
        when(sitePageMapper.toDTOList(Collections.singletonList(mockSitePage))).thenReturn(Collections.singletonList(mockSitePageServiceDto));

        List<AdvertisingServiсeDto> result = advertisingService.getAllAdvertising();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(mockAdvertisingServiсeDto, result.get(0));
    }

    @Test
    void saveAdvertisingNewAdvertising() {
        AdvertisingServiсeDto mockAdvertisingServiсeDto = new AdvertisingServiсeDto();
        Advertising mockAdvertising = new Advertising();

        when(advertisingMapper.toEntity(mockAdvertisingServiсeDto)).thenReturn(mockAdvertising);
        when(advertisingRepository.existsById(any())).thenReturn(false);
        when(advertisingRepository.save(mockAdvertising)).thenReturn(mockAdvertising);
        when(advertisingMapper.toDTO(mockAdvertising)).thenReturn(mockAdvertisingServiсeDto);

        AdvertisingServiсeDto result = advertisingService.saveAdvertising(mockAdvertisingServiсeDto);

        assertEquals(mockAdvertisingServiсeDto, result);
    }

    @Test
    void saveAdvertisingNewAdvertisingWithNewPage() {
        AdvertisingServiсeDto inputDto = new AdvertisingServiсeDto();
        inputDto.setInfoText("Info text");

        SitePageServiceDto sitePageServiceDto = new SitePageServiceDto();
        sitePageServiceDto.setNamePage("Name page");
        inputDto.setSitePageList(Collections.singletonList(sitePageServiceDto));

        Advertising mockAdvertising = new Advertising();
        mockAdvertising.setInfoText("Info text");
        SitePage mockSitePage = new SitePage();
        mockSitePage.setNamePage("Name page");

        when(advertisingRepository.existsById(any())).thenReturn(false);
        when(advertisingMapper.toEntity(inputDto)).thenReturn(mockAdvertising);
        when(advertisingRepository.save(mockAdvertising)).thenReturn(mockAdvertising);
        when(advertisingMapper.toDTO(mockAdvertising)).thenReturn(inputDto);

        when(sitePageRepository.existsById(any())).thenReturn(false);
        when(sitePageMapper.toEntity(sitePageServiceDto)).thenReturn(mockSitePage);
        when(sitePageRepository.save(mockSitePage)).thenReturn(mockSitePage);

        AdvertisingServiсeDto resultDto = advertisingService.saveAdvertising(inputDto);

        assertEquals(inputDto.getInfoText(), resultDto.getInfoText());
        assertNotNull(resultDto.getSitePageList());
        assertEquals(0, resultDto.getSitePageList().size());
    }

    @Test
    void deleteAdvertisingTest() {
        SitePage mockSitePage1 = new SitePage();
        SitePage mockSitePage2 = new SitePage();
        List<SitePage> mockSitePageList = Arrays.asList(mockSitePage1, mockSitePage2);

        when(sitePageRepository.findByAdvertisingListId(testId)).thenReturn(mockSitePageList);

        advertisingService.deleteAdvertising(testId);

        verify(advertisingRepository, times(1)).deleteById(testId);
    }

    @Test
    void saveAdvertisingUpdateExistingAdvertising() {

        AdvertisingServiсeDto inputDto = new AdvertisingServiсeDto();
        inputDto.setId(testId);
        inputDto.setInfoText("Updated InfoText");

        Advertising existingAdvertising = new Advertising();
        existingAdvertising.setId(testId);
        existingAdvertising.setInfoText("Original InfoText");

        when(advertisingRepository.existsById(any(Long.class))).thenReturn(true);
        when(advertisingRepository.findById(any(Long.class))).thenReturn(Optional.of(existingAdvertising));
        when(advertisingRepository.save(any(Advertising.class))).thenReturn(existingAdvertising);
        when(advertisingMapper.toDTO(any(Advertising.class))).thenReturn(inputDto);

        AdvertisingServiсeDto resultDto = advertisingService.saveAdvertising(inputDto);

        assertEquals(inputDto.getInfoText(), resultDto.getInfoText());
    }

    @Test
    void saveAdvertisingNotFound() {
        AdvertisingServiсeDto inputDto = new AdvertisingServiсeDto();
        inputDto.setId(testId);
        inputDto.setInfoText("Updated InfoText");

        when(advertisingRepository.existsById(testId)).thenReturn(true);
        when(advertisingRepository.findById(testId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> advertisingService.saveAdvertising(inputDto));
    }
}