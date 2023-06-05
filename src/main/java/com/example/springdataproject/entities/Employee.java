package com.example.springdataproject.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employee")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Long id;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "departement_id")
    private Departement departement;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "folder_id")
    private Folder folder;

    @Embedded
    private Contact contact;

}

