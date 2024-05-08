package com.poc.makemytrip.dao;

import com.poc.makemytrip.entity.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AircraftDao extends JpaRepository<Aircraft, Integer> {
}
