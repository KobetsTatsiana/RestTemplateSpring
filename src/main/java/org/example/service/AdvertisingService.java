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
import java.util.List;

@Service
public class AdvertisingService {
    private final AdvertisingRepository advertisingRepository;
    private final SitePageRepository sitePageRepository;
    private final AdvertisingMapper advertisingMapper;
    private final SitePageMapper sitePageMapper;

    @Autowired
    public AdvertisingService(AdvertisingRepository advertisingRepository,
                              SitePageRepository sitePageRepository,
                              AdvertisingMapper advertisingMapper,
                              SitePageMapper sitePageMapper) {
        this.advertisingRepository = advertisingRepository;
        this.sitePageRepository = sitePageRepository;
        this.advertisingMapper = advertisingMapper;
        this.sitePageMapper = sitePageMapper;
    }

    @Transactional
    public AdvertisingServiсeDto getAdvertisingById(Long id) {
        Advertising advertising = advertisingRepository.findById( id )
                .orElseThrow( () -> new RuntimeException( "Advertising not found" ) );
        AdvertisingServiсeDto dto = advertisingMapper.toDTO( advertising );
        dto.setSitePageList( sitePageMapper.toDTOList( getSitePageList( dto.getId() ) ) );
        return dto;
    }

    @Transactional
    public List<AdvertisingServiсeDto> getAllAdvertising() {
        List<Advertising> advertisingList = advertisingRepository.findAll();
        List<AdvertisingServiсeDto> advertisingDTOList = advertisingMapper.toDTOList( advertisingList );
        for (AdvertisingServiсeDto advertisingServiсeDto : advertisingDTOList) {
            advertisingServiсeDto.setSitePageList( sitePageMapper.toDTOList( getSitePageList( advertisingServiсeDto.getId() ) ) );
        }
        return advertisingDTOList;
    }

    @Transactional
    public AdvertisingServiсeDto saveAdvertising(AdvertisingServiсeDto dto) {
        Advertising advertising;

        if (dto.getId() != null && advertisingRepository.existsById( dto.getId() )) {
            advertising = advertisingRepository.findById( dto.getId() ).orElseThrow( () -> new RuntimeException( "Advertising not found!" ) );
            advertising.setInfoText( dto.getInfoText() );
        } else {
            advertising = advertisingMapper.toEntity( dto );
        }

        advertising = advertisingRepository.save( advertising );

        if (dto.getSitePageList() != null && !dto.getSitePageList().isEmpty()) {
            for (SitePageServiceDto sitePageServiceDto : dto.getSitePageList()) {
                SitePage sitePage;

                if (sitePageServiceDto.getId() != null && sitePageRepository.existsById( sitePageServiceDto.getId() )) {
                    sitePage = sitePageRepository.findById( sitePageServiceDto.getId() ).orElseThrow( () -> new RuntimeException( "Page not found!" ) );
                    sitePage.setNamePage( sitePageServiceDto.getNamePage() );
                } else {
                    sitePage = sitePageMapper.toEntity( sitePageServiceDto );
                }

                sitePage.addAdvertising(advertising);
                sitePageRepository.save( sitePage );
            }
        }

        List<SitePage> sitePageList = getSitePageList( advertising.getId() );
        AdvertisingServiсeDto savedDto = advertisingMapper.toDTO( advertising );
        savedDto.setSitePageList( sitePageMapper.toDTOList( sitePageList ) );
        return savedDto;
    }


    private List<SitePage> getSitePageList(Long id) {
        List<SitePage> sitePageList = sitePageRepository.findByAdvertisingListId( id );
        for (SitePage sitePage : sitePageList) {
            sitePage.setAdvertisingList( null );
        }
        return sitePageList;
    }

    @Transactional
    public void deleteAdvertising(Long id) {
        List<SitePage> sitePageList = sitePageRepository.findByAdvertisingListId( id );
        for (SitePage sitePage : sitePageList) {
            sitePageRepository.deleteById( sitePage.getId() );
        }
        advertisingRepository.deleteById( id );

    }
}

