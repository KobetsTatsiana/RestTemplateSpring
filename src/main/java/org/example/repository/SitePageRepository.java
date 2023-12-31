package org.example.repository;

import org.example.model.SitePage;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SitePageRepository extends JpaRepository<SitePage, Long> {
    List<SitePage> findByAdvertisingListId(Long advertisingId);
}

