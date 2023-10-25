package org.example.service.dto.mapper;

import org.example.model.Advertising;
import org.example.service.dto.AdvertisingServiсeDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdvertisingMapperTest {

    private AdvertisingMapper advertisingMapper;

    @BeforeEach
    public void setUp() {
        advertisingMapper = Mappers.getMapper(AdvertisingMapper.class);
    }

    @Test
    void testToDTO() {
        Advertising entity = new Advertising();
        entity.setId(1L);
        entity.setInfoText("InfoText");

        AdvertisingServiсeDto result = advertisingMapper.toDTO(entity);

        assertEquals(entity.getId(), result.getId());
        assertEquals(entity.getInfoText(), result.getInfoText());
        assertNull(result.getSitePageList());
    }

    @Test
    void testToEntity() {
        AdvertisingServiсeDto dto = new AdvertisingServiсeDto();
        dto.setId(1L);
        dto.setInfoText("InfoText next");


        Advertising result = advertisingMapper.toEntity(dto);

        assertEquals(dto.getId(), result.getId());
        assertEquals(dto.getInfoText(), result.getInfoText());

    }

    @Test
    void testToDTOList() {
        Advertising entity1 = new Advertising();
        entity1.setId(1L);
        entity1.setInfoText("InfoText 1");


        Advertising entity2 = new Advertising();
        entity2.setId(2L);
        entity2.setInfoText("InfoText 2");

        List<Advertising> advertisingList = Arrays.asList(entity1, entity2);

        List<AdvertisingServiсeDto> results = advertisingMapper.toDTOList(advertisingList);

        assertEquals(advertisingList.size(), results.size());
        assertEquals(entity1.getId(), results.get(0).getId());
        assertEquals(entity1.getInfoText(), results.get(0).getInfoText());

        assertNull(results.get(0).getSitePageList());
        assertEquals(entity2.getId(), results.get(1).getId());
        assertEquals(entity2.getInfoText(), results.get(1).getInfoText());
        assertNull(results.get(1).getSitePageList());
    }

    @Test
    void testToEntityList() {
        AdvertisingServiсeDto dto1 = new AdvertisingServiсeDto();
        dto1.setId(1L);
        dto1.setInfoText("InfoText 1");

        AdvertisingServiсeDto dto2 = new AdvertisingServiсeDto();
        dto2.setId(1L);
        dto2.setInfoText("InfoText 2");

        List<AdvertisingServiсeDto> dtos = Arrays.asList(dto1, dto2);

        List<Advertising> results = advertisingMapper.toEntityList(dtos);

        assertEquals(dtos.size(), results.size());
        assertEquals(dto1.getId(), results.get(0).getId());
        assertEquals(dto1.getInfoText(), results.get(0).getInfoText());
        assertEquals(dto2.getId(), results.get(1).getId());
        assertEquals(dto2.getInfoText(), results.get(1).getInfoText());
    }
}
