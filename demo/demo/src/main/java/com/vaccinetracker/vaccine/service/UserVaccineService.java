package com.vaccinetracker.vaccine.service;

import com.vaccinetracker.vaccine.entity.UserVaccine;
import com.vaccinetracker.vaccine.repository.UserVaccineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserVaccineService {

    private final UserVaccineRepository repository;

    // get my vaccines
    public List<UserVaccine> getMyVaccines(String email) {
        return repository.findByUserEmail(email);
    }

    // take a dose
    public UserVaccine takeDose(String email, String vaccineName, int dose) {

        LocalDate today = LocalDate.now();

        // simple logic: next dose after 30 days
        LocalDate nextDue = (dose >= 3) ? null : today.plusDays(30);

        UserVaccine record = UserVaccine.builder()
                .userEmail(email)
                .vaccineName(vaccineName)
                .doseNumber(dose)
                .takenDate(today)
                .nextDueDate(nextDue)
                .build();

        return repository.save(record);
    }
    public List<UserVaccine> getDueVaccines(String email) {
        LocalDate today = LocalDate.now();

        return repository.findByUserEmail(email)
                .stream()
                .filter(v -> v.getNextDueDate() != null &&
                        !v.getNextDueDate().isAfter(today.plusDays(7)))
                .toList();
    }

}
