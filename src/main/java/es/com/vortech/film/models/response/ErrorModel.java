package es.com.vortech.film.models.response;

import es.com.vortech.film.services.MovieService;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
public class ErrorModel {
    private String errorMessage;
    private LocalDateTime date;
}
