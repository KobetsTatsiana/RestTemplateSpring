package org.example.repository;

import org.example.model.Advertising;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertisingRepository extends JpaRepository<Advertising, Long> {
}

