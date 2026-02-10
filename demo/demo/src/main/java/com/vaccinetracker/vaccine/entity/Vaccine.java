package com.vaccinetracker.vaccine.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vaccines")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;        // Covid-19, Hepatitis B
    private int totalDoses;     // how many doses required
    private int gapDays;        // days between doses
}
