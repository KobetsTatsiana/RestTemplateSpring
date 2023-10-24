package org.example.service.dto;

import java.util.List;

public class AdvertisingServiсeDto {

    private Long id;

    private String infoText;

    private List<SitePageServiceDto> sitePageList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInfoText() {
        return infoText;
    }

    public void setInfoText(String infoText) {
        this.infoText = infoText;
    }

    public List<SitePageServiceDto> getSitePageList() {
        return sitePageList;
    }

    public void setSitePageList(List<SitePageServiceDto> sitePageList) {
        this.sitePageList = sitePageList;
    }
}
