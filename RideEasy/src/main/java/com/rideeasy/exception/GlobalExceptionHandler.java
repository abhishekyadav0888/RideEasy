package com.rideeasy.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {


    //to handle specific InvalidInputException
    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<MyErrorDetails> myInputExceptionHandler(InvalidInputException ie, WebRequest req)  {

        MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(), ie.getMessage(), req.getDescription(false));

        ResponseEntity<MyErrorDetails> re=new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

        return re;
    }

    //to handle specific NotFoundException
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<MyErrorDetails> myNotFoundHandler(NotFoundException ie, WebRequest req)  {

        MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(), ie.getMessage(), req.getDescription(false));

        ResponseEntity<MyErrorDetails> re=new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

        return re;
    }

    //to handle specific RideEasyException
    @ExceptionHandler(RideEasyException.class)
    public ResponseEntity<MyErrorDetails> myRideEasyHandler(RideEasyException ie, WebRequest req)  {

        MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(), ie.getMessage(), req.getDescription(false));

        ResponseEntity<MyErrorDetails> re=new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

        return re;
    }



    //to handle generic any type of Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MyErrorDetails> myExceptionHandler(Exception e,WebRequest req)  {


        MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(), e.getMessage(), req.getDescription(false));

        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);

    }

    //to handle Not found exception
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<MyErrorDetails> mynotFoundHandler(NoHandlerFoundException nfe,WebRequest req)  {


        MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(), nfe.getMessage(), req.getDescription(false));

        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);

    }


    //Validation Error Handler
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MyErrorDetails> myMANVExceptionHandler(MethodArgumentNotValidException me)  {


        MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(),"Validation Error",me.getBindingResult().getFieldError().getDefaultMessage());

        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);

    }

}
