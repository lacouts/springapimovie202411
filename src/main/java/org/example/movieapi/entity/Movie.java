package org.example.movieapi.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.movieapi.enums.MovieColor;

import java.util.List;
import java.util.Set;

// lombok: NB: don't use @Data with Hibernate
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString(of = {"id", "title", "year"})
// JPA
@Entity // this class is handled by Hibernate
    // @Table(name = "t_movie") // Tuning: tablename, indexes, schema, uniqueConstraints
@NamedEntityGraph(
        name="Movie.directorAndActors",
        attributeNodes = {
                @NamedAttributeNode("director"),
                @NamedAttributeNode("actors")
        }
)
public class Movie {
    // NB: by default all attributes are persistent except if annotated with @Transient

    // NB: strategy for generating primary key
    // - IDENTITY: auto_increment, serial, identity clause on this column
    //          hibernate execute INSERT without pk, then the db generate the pk
    // - SEQUENCE: a sequence is created in db
    //          hibernate calls sequence, then execute INSERT with this pk
    @Id // primary key + unique +  not null
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // NB: ddl.auto=none, must map allocation_size = 50
    // NB: strategy can be replaced (or complemented) with generator and @SequenceGenerator
    //    @GeneratedValue(generator = "movie_seq") // NB: ddl.auto=none, choose your seq name and allocationSize
    //    @SequenceGenerator(name = "movie_seq", sequenceName = "movie_seq", allocationSize = 1)
    private Integer id;

    @Column(nullable = false, length = 300)
    private String title;

    // @Column(name = "release_year")
    private int year;  // primitive type => nullable = false

    private Integer duration; // Object type => nullable = true

    @Column(length = 4000)
    private String synopsis;

    @Enumerated(EnumType.STRING) // default: ORDINAL
    private MovieColor color;

    // @Transient: before association mapping
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "director_id") // default: nullable = true
    private Person director;

    @ManyToMany //(fetch = FetchType.EAGER)
    @JoinTable(
            name = "play",
            joinColumns = @JoinColumn(name = "movie_id"),  // FK: Play.movie_id references movie(id)
            inverseJoinColumns = @JoinColumn(name = "actor_id") // FK: Play.actor_id references person(id)
    )
    private Set<Person> actors;

}
