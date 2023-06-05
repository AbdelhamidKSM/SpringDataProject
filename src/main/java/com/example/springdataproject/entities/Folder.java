package com.example.springdataproject.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "folder")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "acess_type")
    private String acessType;

    @OneToOne(mappedBy = "folder", cascade = CascadeType.ALL, orphanRemoval = true)
    private Employee employee;

}