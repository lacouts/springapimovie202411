package org.example.movieapi.handler;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DataExceptionHandler {

    // solution 1

//    @ResponseStatus(code= HttpStatus.CONFLICT)
//    @ExceptionHandler(DataIntegrityViolationException.class) // or parent exception: DataAccessException
//    public void handleSpringDataException(){
//        // empty body => HTTP Response body empty
//    }

    // solution 2: with DTO ProblemDetail

    // @ResponseStatus(code= HttpStatus.CONFLICT) // optional if status is set in ProblemDetail response
    @ExceptionHandler(DataIntegrityViolationException.class) // or parent exception: DataAccessException
    public ProblemDetail handleSpringDataException(){
        // empty body => HTTP Response body empty
        var pbd = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        pbd.setProperty("message", "Data integrity error");
        return pbd;
    }

    // NB: handler can take following arguments:
    //    - DataIntegrityViolationException ex (or any parent exception)
    //    - WebRequest request
}
