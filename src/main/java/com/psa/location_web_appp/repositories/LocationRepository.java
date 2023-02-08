package com.psa.location_web_appp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.psa.location_web_appp.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

}
