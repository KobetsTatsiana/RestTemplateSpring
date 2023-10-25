package org.example.service.dto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserEntityServiceDtoTest {

    @Test
    void testGetId() {
        UserEntityServiceDto userEntityServiceDto = new UserEntityServiceDto();
        Long id = 1L;
        userEntityServiceDto.setId(id);
        assertEquals(id, userEntityServiceDto.getId());
    }

    @Test
    void testGetName() {
        UserEntityServiceDto userEntityServiceDto = new UserEntityServiceDto();
        String name = "Name1";
        userEntityServiceDto.setName(name);
        assertEquals(name, userEntityServiceDto.getName());
    }

    @Test
    void testGetSurname() {
        UserEntityServiceDto userEntityServiceDto = new UserEntityServiceDto();
        String surname = "Surname1";
        userEntityServiceDto.setSurname(surname);
        assertEquals(surname, userEntityServiceDto.getSurname());
    }

    @Test
    void testGetAddress() {
        UserEntityServiceDto userEntityServiceDto = new UserEntityServiceDto();
        String address = "Address1";
        userEntityServiceDto.setAddress(address);
        assertEquals(address, userEntityServiceDto.getAddress());
    }

    @Test
    void testGetUserSiteList() {
        UserEntityServiceDto userEntityServiceDto = new UserEntityServiceDto();
        List<UserSiteServiceDto> userSiteList = new ArrayList<>();
        UserSiteServiceDto userSiteServiceDto = new UserSiteServiceDto();
        userSiteList.add(userSiteServiceDto);
        userEntityServiceDto.setUserSiteList(userSiteList);
        assertEquals(userSiteList, userEntityServiceDto.getUserSiteList());
    }

    @Test
    void testGetIdWhenNotSet() {
        UserEntityServiceDto userEntityServiceDto = new UserEntityServiceDto();
        assertNull(userEntityServiceDto.getId());
    }

    @Test
    void testGetNameWhenNotSet() {
        UserEntityServiceDto userEntityServiceDto = new UserEntityServiceDto();
        assertNull(userEntityServiceDto.getName());
    }

    @Test
    void testGetSurnameWhenNotSet() {
        UserEntityServiceDto userEntityServiceDto = new UserEntityServiceDto();
        assertNull(userEntityServiceDto.getSurname());
    }

    @Test
    void testGetAddressWhenNotSet() {
        UserEntityServiceDto userEntityServiceDto = new UserEntityServiceDto();
        assertNull(userEntityServiceDto.getAddress());
    }

    @Test
    void testGetUserSiteListWhenNotSet() {
        UserEntityServiceDto userEntityServiceDto = new UserEntityServiceDto();
        assertTrue(userEntityServiceDto.getUserSiteList().isEmpty());
    }
}