package com.bswork.helper.api.exception;

import com.bswork.helper.dataprovider.exception.StoryNotFoundException;
import com.bswork.helper.dataprovider.exception.TaskNotFoundException;
import com.bswork.helper.domain.exception.*;
import com.bswork.helper.dataprovider.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        List<Problema.Campo> campos = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String nome = ((FieldError) error).getField();
            String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            campos.add(new Problema.Campo(nome, mensagem));
        }

        Problema problema = new Problema();
        problema.setStatus(status.value());
        problema.setDataHora(OffsetDateTime.now());
        problema.setTitulo("Um ou mais campos est??o inv??lidos. Fa??a o preenchimento correto e tente novamente!");
        problema.setCampos(campos);

        return handleExceptionInternal(ex, problema, headers, status, request);
    }

    @ExceptionHandler({ NoContentUserException.class, NoContentStoryException.class, NoContentTaskException.class })
    public ResponseEntity<Object> handleNoContent(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.NO_CONTENT;
        return handleExceptionInternal(ex, new Problema(), new HttpHeaders(), status, request);
    }

    @ExceptionHandler({ UserNotFoundException.class, StoryNotFoundException.class, TaskNotFoundException.class})
    public ResponseEntity<Object> handleUserNotFound(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        Problema problema = new Problema();
        problema.setStatus(status.value());
        problema.setDataHora(OffsetDateTime.now());
        problema.setTitulo(ex.getMessage());
        problema.setUrl(UrlExceptionEnum.getUrlForException(status.value()));

        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    @ExceptionHandler({ ValueInvalidException.class })
    public ResponseEntity<Object> handleValueInvalid(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Problema problema = new Problema();
        problema.setStatus(status.value());
        problema.setDataHora(OffsetDateTime.now());
        problema.setTitulo(ex.getMessage());
        problema.setUrl(UrlExceptionEnum.getUrlForException(status.value()));

        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    @ExceptionHandler({ UserPresentException.class, BusyException.class })
    public ResponseEntity<Object> handleUserPresent(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        Problema problema = new Problema();
        problema.setStatus(status.value());
        problema.setDataHora(OffsetDateTime.now());
        problema.setTitulo(ex.getMessage());
        problema.setUrl(UrlExceptionEnum.getUrlForException(status.value()));

        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }
}
