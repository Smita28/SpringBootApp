package com.poc.project.EmployeDataCrud.Exception;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.poc.project.EmployeDataCrud.controller.EmployeeController;
 
@EnableWebMvc
@ControllerAdvice(basePackages = "com.poc.project.EmployeDataCrud")
public class CustomExceptionHandler  
{
	private static final Logger logger    = Logger.getLogger(EmployeeController.class.getName());

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Server Error", details);
        logger.error("Exception: ",ex);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
 
    @ExceptionHandler(InvalidInputException.class)
    public final ResponseEntity<Object> handleInvalidInputException(InvalidInputException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Invalid Input", details);
        logger.error("Invalid Input Exception: ",ex);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
 
  
}

