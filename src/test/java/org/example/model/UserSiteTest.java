package org.example.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UserSiteTest {

    private UserSite userSite;
    private final Long id = 1L;
    private final UserEntity userEntity = new UserEntity();
    private final String nameSite = "nameSite";

    @BeforeEach
    public void setUp() {
        userSite = new UserSite();
        userSite.setId(id);
        userSite.setUserId(userEntity);
        userSite.setNameSite(nameSite);
    }

    @Test
    void testEqualsSameObject() {
        UserSite userSite = new UserSite(1L, "New nameSite");
        assertEquals(userSite, userSite);
    }

    @Test
    void testEqualsNullObject() {
        UserSite userSite = new UserSite(1L, "New nameSite");
        assertNotEquals(null, userSite);
    }

    @Test
    void testEqualsDifferentClass() {
        UserSite userSite = new UserSite(1L, "New nameSite");
        UserEntity user = mock(UserEntity.class);
        assertNotEquals(userSite, user);
    }

    @Test
    void testEqualsWithSameClass() {
        UserSite userSite1 = new UserSite(1L, "New 1 nameSite");
        UserSite userSite2 = new UserSite(2L, "New 2 nameSite");
        assertNotEquals(userSite1, userSite2);
    }

    @Test
    void gettersAndSettersWorkCorrectly() {
        assertThat(userSite.getId()).isEqualTo(id);
        assertThat(userSite.getUserId()).isEqualTo(userEntity);
        assertThat(userSite.getNameSite()).isEqualTo(nameSite);
    }

    @Test
    void equalsAndHashCodeWorkCorrectly() {
        UserSite anotherUserSite = new UserSite(id, nameSite);
        anotherUserSite.setUserId(userEntity);

        assertThat(userSite).isEqualTo(anotherUserSite);
        assertThat(userSite.hashCode()).isEqualTo(anotherUserSite.hashCode());

        anotherUserSite.setNameSite("Different nameSite");
        assertThat(userSite).isNotEqualTo(anotherUserSite);
    }

    @Test
    public void toStringTest() {
        UserEntity userEntity = new UserEntity();
        UserSite userSite = new UserSite();
        userSite.setId(1L);
        userSite.setNameSite("nameSite");
        userSite.setUserId(userEntity);

        String expected = "UserSite{" +
                "id=" + userSite.getId() +
                ", userId=" + userEntity.toString() +
                ", nameSite='" + userSite.getNameSite() + '\'' +
                '}';

        assertEquals(expected, userSite.toString());
    }
}

