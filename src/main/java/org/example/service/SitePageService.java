package org.example.service;

import org.example.model.Advertising;
import org.example.model.SitePage;
import org.example.repository.AdvertisingRepository;
import org.example.repository.SitePageRepository;
import org.example.service.dto.AdvertisingServiсeDto;
import org.example.service.dto.SitePageServiceDto;
import org.example.service.dto.mapper.AdvertisingMapper;
import org.example.service.dto.mapper.SitePageMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SitePageService {
    private final SitePageRepository sitePageRepository;
    private final AdvertisingRepository advertisingRepository;
    private final SitePageMapper sitePageMapper;
    private final AdvertisingMapper advertisingMapper;

    @Autowired
    public SitePageService(
            SitePageRepository sitePageRepository,
            AdvertisingRepository advertisingRepository,
            SitePageMapper sitePageMapper,
            AdvertisingMapper advertisingMapper) {
        this.sitePageRepository = sitePageRepository;
        this.advertisingRepository = advertisingRepository;
        this.sitePageMapper = sitePageMapper;
        this.advertisingMapper = advertisingMapper;
    }

    @Transactional
    public SitePageServiceDto createOrUpdatePage(SitePageServiceDto sitePageServiceDto) {
        Set<Advertising> advertisingSet = new HashSet<>();

        for (AdvertisingServiсeDto advertisingServiсeDto : sitePageServiceDto.getAdvertisingList()) {
            Advertising advertising;

            if (advertisingServiсeDto.getId() != null) {
                advertising = advertisingRepository.findById(advertisingServiсeDto.getId()).orElse(null);
                if (advertising != null) {
                    advertising.setInfoText(advertisingServiсeDto.getInfoText());
                } else {
                    advertising = advertisingMapper.toEntity(advertisingServiсeDto);
                }
            } else {
                advertising = advertisingMapper.toEntity(advertisingServiсeDto);
            }

            advertising = advertisingRepository.save(advertising);
            advertisingSet.add(advertising);
        }

        SitePage sitePage;
        if (sitePageServiceDto.getId() != null) {
            sitePage = sitePageRepository.findById(sitePageServiceDto.getId()).orElse(null);
            if (sitePage != null) {
                sitePage.setNamePage(sitePageServiceDto.getNamePage());
            } else {
                sitePage = sitePageMapper.toEntity(sitePageServiceDto);
            }
        } else {
            sitePage = sitePageMapper.toEntity(sitePageServiceDto);
        }

        sitePage.setAdvertisingList(new ArrayList<>(advertisingSet));
        sitePage = sitePageRepository.save(sitePage);
        return sitePageMapper.toDTO(sitePage);
    }

    @Transactional
    public List<SitePageServiceDto> getAllPages() {
        List<SitePage> sitePageList = sitePageRepository.findAll();
        List<SitePageServiceDto> sitePageServiceDtos = sitePageMapper.toDTOList(sitePageList);

        for (int i = 0; i < sitePageList.size(); i++) {
            SitePage sitePage = sitePageList.get(i);
            SitePageServiceDto sitePageServiceDto = sitePageServiceDtos.get(i);
            sitePageServiceDto.setAdvertisingList(advertisingMapper.toDTOList(sitePage.getAdvertisingList()));
        }

        return sitePageServiceDtos;
    }

    public SitePageServiceDto getPageById(Long id) {
        SitePage sitePage = sitePageRepository.findById(id).orElseThrow(() -> new RuntimeException("SitePage not found"));

        SitePageServiceDto sitePageServiceDto = sitePageMapper.toDTO(sitePage);

        sitePageServiceDto.setAdvertisingList(advertisingMapper.toDTOList(sitePage.getAdvertisingList()));

        return sitePageServiceDto;
    }

    @Transactional
    public void deletePage(Long id) {
        sitePageRepository.deleteById(id);
    }
}

