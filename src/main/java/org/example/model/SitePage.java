package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class SitePage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String namePage;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Advertising> advertisingList = new ArrayList<>();

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

    public List<Advertising> getAdvertisingList() {
        return advertisingList;
    }

    public void setAdvertisingList(List<Advertising> advertisingList) {
        this.advertisingList = advertisingList;
    }

    public void addAdvertising(Advertising advertising) {
        if (this.advertisingList == null) {
            this.advertisingList = new ArrayList<>();
        }

        if (advertising.getSitePageList() == null) {
            advertising.setSitePageList(new ArrayList<>());
        }

        this.advertisingList.add(advertising);
        advertising.getSitePageList().add(this);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SitePage sitePage = (SitePage) o;
        return id == sitePage.id && Objects.equals(namePage, sitePage.namePage) && Objects.equals(advertisingList, sitePage.advertisingList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, namePage, advertisingList);
    }

    @Override
    public String toString() {
        return "SitePage{" +
                "id=" + id +
                ", namePage='" + namePage + '\'' +
                ", advertisingList=" + advertisingList +
                '}';
    }
}
