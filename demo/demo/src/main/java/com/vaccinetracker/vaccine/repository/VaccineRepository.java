package com.vaccinetracker.vaccine.repository;

import com.vaccinetracker.vaccine.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccineRepository extends JpaRepository<Vaccine, Long> {
}
