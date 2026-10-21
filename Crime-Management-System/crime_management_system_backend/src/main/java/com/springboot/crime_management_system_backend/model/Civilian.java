package com.springboot.crime_management_system_backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.Period;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "civilian")
public class Civilian {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(nullable = false)
    private LocalDate dob;

    @Column(nullable = false, length = 20)
    private String aadhaarNumber;

    @Column(nullable = false, length = 20)
    private String panNumber;

    @Transient
    private int age;

    @OneToOne
    private User user;

    public int getAge(){
        if (this.dob != null) {
            return Period.between(this.dob, LocalDate.now()).getYears();
        }
        return 0;
    }
}
