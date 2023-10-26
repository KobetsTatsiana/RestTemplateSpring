package org.example.model;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "userentity")
public class UserEntity {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="surname")
    private String surname;
    @Column(name="address")
    private String address;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<UserSite> userSiteList = new ArrayList<>();

    public UserEntity(){}

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

    public List<UserSite> getUserSiteList() {
        return userSiteList;
    }

    public void setUserSiteList(List<UserSite> userSiteList) {
        this.userSiteList = userSiteList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(address, that.address) && Objects.equals(userSiteList, that.userSiteList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, address, userSiteList);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", address='" + address + '\'' +
                ", userSiteList=" + userSiteList +
                '}';
    }
}
