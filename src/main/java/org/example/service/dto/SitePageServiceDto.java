package org.example.service.dto;

import java.util.List;

public class SitePageServiceDto {

    private Long id;

    private String namePage;

    private List<AdvertisingServiсeDto> advertisingList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamePage() {
        return namePage;
    }

    public void setNamePage(String namePage) {
        this.namePage = namePage;
    }

    public List<AdvertisingServiсeDto> getAdvertisingList() {
        return advertisingList;
    }

    public void setAdvertisingList(List<AdvertisingServiсeDto> advertisingList) {
        this.advertisingList = advertisingList;
    }
}
