package com.algovoltix.evbooking.repository;

import com.algovoltix.evbooking.entity.RateCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RateCardRepository extends JpaRepository<RateCard, UUID> {
}
