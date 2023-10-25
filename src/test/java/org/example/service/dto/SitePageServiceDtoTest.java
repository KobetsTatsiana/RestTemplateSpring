package org.example.service.dto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SitePageServiceDtoTest {

    @Test
    void testGetId() {
        SitePageServiceDto dto = new SitePageServiceDto();

        Long id = 1L;
        dto.setId(id);

        assertEquals(id, dto.getId());
    }

    @Test
    void testGetNamePage() {
        SitePageServiceDto dto = new SitePageServiceDto();

        String namePage = "Name Page";
        dto.setNamePage(namePage);

        assertEquals(namePage, dto.getNamePage());
    }

    @Test
    void testGetAdvertisingList() {
        SitePageServiceDto dto = new SitePageServiceDto();
        List<AdvertisingServiсeDto> advertisingServiсeDtoList = new ArrayList<>();
        AdvertisingServiсeDto advertisingServiсeDto1 = new AdvertisingServiсeDto();
        AdvertisingServiсeDto advertisingServiсeDto2 = new AdvertisingServiсeDto();
        advertisingServiсeDtoList.add(advertisingServiсeDto1);
        advertisingServiсeDtoList.add(advertisingServiсeDto2);
        dto.setAdvertisingList(advertisingServiсeDtoList);
        assertEquals(advertisingServiсeDtoList, dto.getAdvertisingList());
    }


    @Test
    void testGetIdWhenNotSet() {
        SitePageServiceDto dto = new SitePageServiceDto();

        assertNull(dto.getId());
    }

    @Test
    void testGetNamePageWhenNotSet() {
        SitePageServiceDto dto = new SitePageServiceDto();

        assertNull(dto.getNamePage());
    }

    @Test
    void testGetAdvertisingListWhenNotSet() {
        SitePageServiceDto dto = new SitePageServiceDto();

        assertNull(dto.getAdvertisingList());
    }
}