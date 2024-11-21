package org.example.movieapi.entity;

import jakarta.persistence.*;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;

@ToString
@Entity
public class Person {
    private Integer id;
    private String name;
    private LocalDate birthdate;

    // needed by JPA
    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, LocalDate birthdate) {
        this.name = name;
        this.birthdate = birthdate;
    }

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @GeneratedValue(generator = "person_seq") // ok with all values for generate.ddl
    @SequenceGenerator(name = "person_seq", sequenceName = "person_seq", allocationSize = 50)
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

    // Bidrectional association (Be careful with 'update' scenario !)
//    @OneToMany(mappedBy = "director")
//    public Set<Movie> directedMovies;
//
//    @ManyToMany(mappedBy = "actors")
//    public Set<Movie> playedMovies;

}
