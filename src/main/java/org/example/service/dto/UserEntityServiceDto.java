package org.example.service.dto;

import java.util.ArrayList;
import java.util.List;

public class UserEntityServiceDto {
    private Long id;
    private String name;
    private String surname;
    private String address;
    private List<UserSiteServiceDto> userSiteList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<UserSiteServiceDto> getUserSiteList() {
        if (this.userSiteList == null) {
            this.userSiteList = new ArrayList<>();
        }
        return this.userSiteList;
    }

    public void setUserSiteList(List<UserSiteServiceDto> userSiteList) {
        this.userSiteList = userSiteList;
    }
}
