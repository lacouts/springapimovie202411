package org.example.movieapi.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE) // with offset: Ordered.HIGHEST_PRECEDENCE + 10
public class CustomSpringControllerAdvice
        extends ResponseEntityExceptionHandler
        // ~15 default handlers (validation, path, method not allowed, not acceptable, ...)
{

    // custom handler for Validation errors
    //      overrides default handler for this error
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var pbd = ProblemDetail.forStatus(HttpStatus.I_AM_A_TEAPOT);
        pbd.setProperty("message", "Argument not valid");
        // TODO: produce a more concise DTO describing validation errors from MethodArgumentNotValidException ex
        pbd.setProperty("validation_errors", ex.getAllErrors());
        return ResponseEntity.of(pbd).build();
                // OR: .status(pbd.getStatus()).body(pbd);
    }


}
