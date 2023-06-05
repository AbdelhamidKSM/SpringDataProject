package com.example.springdataproject.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;


@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Contact {
    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile")
    private String mobile;
}