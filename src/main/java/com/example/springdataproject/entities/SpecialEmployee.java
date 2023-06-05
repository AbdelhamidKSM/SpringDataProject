package com.example.springdataproject.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "special_employee")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SpecialEmployee extends Employee {
    public enum SenioritySecondLevel {EXPERT,SENIOR }

    @Enumerated (EnumType.STRING)
    @Column(name = "seniority" ,nullable = false)
    public SenioritySecondLevel seniority ;


    @Column(name = "profile" ,nullable = false)
    private String profile;


    @Column(name = "tjm")
    private Double tjm;

}