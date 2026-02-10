package com.vaccinetracker.vaccine.controller;

import com.vaccinetracker.vaccine.entity.UserVaccine;
import com.vaccinetracker.vaccine.service.UserVaccineService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-vaccines")
@RequiredArgsConstructor
@CrossOrigin
public class UserVaccineController {

    private final UserVaccineService service;

    // get logged user email from JWT
    private String getUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @GetMapping
    public List<UserVaccine> myVaccines() {
        return service.getMyVaccines(getUserEmail());
    }

    @PostMapping("/{name}/{dose}")
    public UserVaccine takeDose(@PathVariable String name,
                                @PathVariable int dose) {

        return service.takeDose(getUserEmail(), name, dose);
    }
    @GetMapping("/reminders")
    public List<UserVaccine> reminders() {
        return service.getDueVaccines(getUserEmail());
    }

}
