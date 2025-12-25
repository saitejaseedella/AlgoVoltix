package com.algovoltix.evbooking.repository;

import com.algovoltix.evbooking.entity.StationOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StationOwnerRepository extends JpaRepository<StationOwner, UUID> {
}
