package org.example.movieapi.dto;

public interface IMovieDtoStatsByYear {
    int getYear();
    long getMovieCount();
    Integer getMinDuration();
    Integer getMaxDuration();
    Double getAvgDuration();
}
