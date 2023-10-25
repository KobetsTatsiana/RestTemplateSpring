package org.example.service.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserSiteServiceDtoTest {

    @Test
    void testGetId() {
        UserSiteServiceDto userSiteServiceDto = new UserSiteServiceDto();

        Long id = 1L;
        userSiteServiceDto.setId( id );

        assertEquals(id, userSiteServiceDto.getId());
    }

    @Test
    void testGetNameSite() {
        UserSiteServiceDto userSiteServiceDto = new UserSiteServiceDto();

        String nameSite = "nameSite";
        userSiteServiceDto.setNameSite(nameSite);

        assertEquals(nameSite, userSiteServiceDto.getNameSite());
    }

    @Test
    void testGetIdWhenNotSet() {
        UserSiteServiceDto userSiteServiceDto = new UserSiteServiceDto();

        assertNull(userSiteServiceDto.getId());
    }

    @Test
    void testGetNameSiteWhenNotSet() {
        UserSiteServiceDto userSiteServiceDto = new UserSiteServiceDto();

        assertNull(userSiteServiceDto.getNameSite());
    }
}