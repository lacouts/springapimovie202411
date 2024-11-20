package org.example.movieapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.movieapi.enums.MovieColor;

// JSON serialization tuning
@JsonInclude(JsonInclude.Include.NON_NULL)

// lombok
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString(exclude = {"synopsis", "color"})
public class MovieDtoCreate {
    // list of all bean validation constraints: https://jakarta.ee/specifications/bean-validation/3.0/apidocs/
    // Examples: https://www.baeldung.com/java-validation
    //          https://www.baeldung.com/javax-validation-method-constraints
    @NotEmpty
    private String title;

    @Min(1888)
    private int year;
    private Integer duration;
    private String synopsis;
    private MovieColor color;
}
