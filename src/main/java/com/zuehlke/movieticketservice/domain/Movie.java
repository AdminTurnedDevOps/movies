package com.zuehlke.movieticketservice.domain;


import java.util.Objects;


public class Movie {

    private int id;
    private String title;
    private String posterUrl;

    public Movie(int id, String title, String posterUrl) {
        this.id = id;
        this.title = title;
        this.posterUrl = posterUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id && Objects.equals(title, movie.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
