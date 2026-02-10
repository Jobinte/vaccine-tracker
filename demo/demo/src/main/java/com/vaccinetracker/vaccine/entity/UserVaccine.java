package com.vaccinetracker.vaccine.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "user_vaccines")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserVaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail;     // which user

    private String vaccineName;   // Covid, Hepatitis etc

    private int doseNumber;       // dose taken (1,2,3)

    private LocalDate takenDate;  // date taken

    private LocalDate nextDueDate; // auto calculated
}
