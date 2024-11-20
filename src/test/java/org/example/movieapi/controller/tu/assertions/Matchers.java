package org.example.movieapi.controller.tu.assertions;


import org.springframework.lang.Nullable;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Objects;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class Matchers {

    public static ResultMatcher jsonPathIsNullOrEquals(
            String path,
            @Nullable Object expectedValue)
    {
        if (Objects.isNull(expectedValue)) return jsonPath(path).doesNotExist();
        else return jsonPath(path).value(expectedValue);
    }

    public static ResultMatcher jsonPathIsNullOrEqualsShort(
            String path,
            @Nullable Short expectedValue
    )
    {
        if (Objects.isNull(expectedValue)) return jsonPath(path).doesNotExist();
        else return jsonPath(path).value(expectedValue.intValue());
    }

}