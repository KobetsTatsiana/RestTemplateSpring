package org.example.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "usersite")
public class UserSite {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userId;

    @Column(name = "namesite")
    private String nameSite;

    public UserSite() {
    }

    public UserSite(String nameSite) {
        this.nameSite = nameSite;
    }

    public UserSite(Long id, String nameSite) {
        this.id = id;
        this.nameSite = nameSite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUserId() {
        return userId;
    }

    public void setUserId(UserEntity userId) {
        this.userId = userId;
    }

    public String getNameSite() {
        return nameSite;
    }

    public void setNameSite(String nameSite) {
        this.nameSite = nameSite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSite userSite = (UserSite) o;
        return id == userSite.id && Objects.equals(userId, userSite.userId) && Objects.equals(nameSite, userSite.nameSite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, nameSite);
    }

    @Override
    public String toString() {
        return "UserSite{" +
                "id=" + id +
                ", userId=" + userId +
                ", nameSite='" + nameSite + '\'' +
                '}';
    }
}
