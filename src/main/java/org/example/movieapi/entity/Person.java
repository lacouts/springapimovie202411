package org.example.movieapi.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Person {
    private Integer id;
    private String name;
    private LocalDate birthdate;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(length = 150, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // @Temporal(TemporalType.DATE) // with Java type: Date or Calendar
    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

}
