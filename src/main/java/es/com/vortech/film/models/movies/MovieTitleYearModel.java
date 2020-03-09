package es.com.vortech.film.models.movies;

public class MovieTitleYearModel {
    private String title;
    private int year;

    public MovieTitleYearModel() {
    }

    public MovieTitleYearModel(String title, int year) {
        this.title = title;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
