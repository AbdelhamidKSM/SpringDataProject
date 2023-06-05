package com.example.springdataproject.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "standard_employee")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StandardEmployee  extends Employee{

    public enum Seniority {OPERATOR,JUNIOR}

    @Enumerated (EnumType.STRING)
    @Column(name = "seniority" ,nullable = false)
    public Seniority seniority ;


    @Column(name = "profile" ,nullable = false)
    private String profile;

    @Column(name = "salary")
    private Double salary;

}