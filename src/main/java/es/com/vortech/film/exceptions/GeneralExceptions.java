package es.com.vortech.film.exceptions;

import es.com.vortech.film.models.response.ErrorModel;
import es.com.vortech.film.models.response.ResponseModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GeneralExceptions extends ResponseEntityExceptionHandler {
    ResponseModel responseModel;
    public GeneralExceptions(){
        responseModel = new ResponseModel();
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        responseModel.setData(ErrorModel.builder()
                    .errorMessage(getErrorMessage(ex).toString())
                    .date(LocalDateTime.now())
                .build());
        return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        responseModel.setData(
                ErrorModel.builder()
                    .errorMessage("Verificar los campos suministrados")
                    .date(LocalDateTime.now())
                .build());
        return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
    }

    private List<String> getErrorMessage(MethodArgumentNotValidException ex){
        return ex.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
    }
}
