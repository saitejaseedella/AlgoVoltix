package com.algovoltix.evbooking.repository;

import com.algovoltix.evbooking.entity.PriceAlgorithm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PriceAlgorithmRepository extends JpaRepository<PriceAlgorithm, UUID> {
}
