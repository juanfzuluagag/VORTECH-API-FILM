package es.com.vortech.film.models;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@Component
public class ResponseModel {
    private LocalDateTime localDateTime= LocalDateTime.now();
    private Object data;
    private String errorMessage;
}
