package org.example.service.dto;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AdvertisingServiceDtoTest {

    private AdvertisingServiсeDto advertisingServiceDto;

    @Test
    void testGetAdvertisingInfoText() {
        AdvertisingServiсeDto advertisingServiсeDto = new AdvertisingServiсeDto();
        String infoText = "Info Text";
        advertisingServiсeDto.setInfoText(infoText);
        assertEquals(infoText, advertisingServiсeDto.getInfoText());
    }

    @Test
    void testGetSitePageList() {
        AdvertisingServiсeDto advertisingServiсeDto = new AdvertisingServiсeDto();
        List<SitePageServiceDto> sitePageServiceDtoList = new ArrayList<>();
        advertisingServiсeDto.setSitePageList(sitePageServiceDtoList);
        assertEquals(sitePageServiceDtoList, advertisingServiсeDto.getSitePageList());
    }

    @Test
    void testGetIdWhenNotSet() {
        AdvertisingServiсeDto advertisingServiсeDto = new AdvertisingServiсeDto();
        assertNull(advertisingServiсeDto.getId());
    }

    @Test
    void testGetInfoTextWhenNotSet() {
        AdvertisingServiсeDto advertisingServiсeDto = new AdvertisingServiсeDto();
        assertNull(advertisingServiсeDto.getInfoText());
    }

    @Test
    void testGetSitePageListWhenNotSet() {
        AdvertisingServiсeDto advertisingServiсeDto = new AdvertisingServiсeDto();
        assertNull(advertisingServiсeDto.getSitePageList());
    }
}
