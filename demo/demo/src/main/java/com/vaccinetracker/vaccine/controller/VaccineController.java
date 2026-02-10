package com.vaccinetracker.vaccine.controller;

import com.vaccinetracker.vaccine.entity.Vaccine;
import com.vaccinetracker.vaccine.service.VaccineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vaccines")
@CrossOrigin
@RequiredArgsConstructor
public class VaccineController {



    private final VaccineService vaccineService;

    @PostMapping
    public Vaccine add(@RequestBody Vaccine vaccine) {
        return vaccineService.addVaccine(vaccine);
    }

    @GetMapping
    public List<Vaccine> getAll() {
        return vaccineService.getAllVaccines();
    }
}
