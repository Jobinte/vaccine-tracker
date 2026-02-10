package com.vaccinetracker.vaccine.repository;

import com.vaccinetracker.vaccine.entity.UserVaccine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserVaccineRepository extends JpaRepository<UserVaccine, Long> {

    List<UserVaccine> findByUserEmail(String email);
}
