package org.example.movieapi.controller.tu.fixture;

import java.util.Objects;

public class JsonProviders {

    private static <T> boolean addField(
            StringBuilder builder,
            String fieldName,
            T fieldValue,
            boolean quoteValue,
            boolean previous)
    {
        if (Objects.isNull(fieldValue)) return previous;
        if (previous) builder.append(", ");
        builder.append('"')
                .append(fieldName)
                .append("\": ");
        if (quoteValue) builder.append('"');
        builder.append(fieldValue);
        if (quoteValue) builder.append('"');
        return true;
    }

    public static String movieJSON(String title, Integer year, Integer duration, String synopsis){
        var builder = new StringBuilder("{");
        boolean previous = false;
        previous = addField(builder, "title", title, true, previous);
        previous = addField(builder, "year", year, false, previous);
        previous = addField(builder, "duration", duration, false, previous);
        addField(builder, "synopsis", synopsis, true, previous);
        return builder.append("}").toString();
    }
}