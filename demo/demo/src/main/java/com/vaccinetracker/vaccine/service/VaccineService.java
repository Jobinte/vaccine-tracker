package com.vaccinetracker.vaccine.service;

import com.vaccinetracker.vaccine.entity.Vaccine;
import com.vaccinetracker.vaccine.repository.VaccineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VaccineService {

    private final VaccineRepository vaccineRepository;

    public Vaccine addVaccine(Vaccine vaccine) {
        return vaccineRepository.save(vaccine);
    }

    public List<Vaccine> getAllVaccines() {
        return vaccineRepository.findAll();
    }
}
