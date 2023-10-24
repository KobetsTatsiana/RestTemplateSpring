package org.example.repository;


import org.example.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
public interface UserEntityRepository  extends JpaRepository<UserEntity, Long> {

    @Query("SELECT us FROM UserEntity us JOIN FETCH us.userSiteList WHERE us.id = :id")
    Optional<UserEntity> findByIdWithArticles(@Param("id") long id);

    @Query("SELECT us FROM UserEntity us JOIN FETCH us.userSiteList")
    List<UserEntity> findAllWithSites();
}
