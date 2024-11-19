package org.example.movieapi.entity;

import jakarta.persistence.*;
import lombok.*;

// lombok: NB: don't use @Data with Hibernate
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString(of = {"id", "title", "year"})
// JPA
@Entity
public class Movie {

    // NB: strategy for generating primary key
    // - IDENTITY: auto_increment, serial, identity clause on this column
    //          hibernate execute INSERT without pk, then the db generate the pk
    // - SEQUENCE: a sequence is created in db
    //          hibernate calls sequence, then execute INSERT with this pk
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String title;

    // @Column(name = "release_year")
    private int year;
}
