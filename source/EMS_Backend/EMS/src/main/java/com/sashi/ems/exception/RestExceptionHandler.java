package com.sashi.ems.exception;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sashi.ems.model.EmsResponse;
import com.sashi.ems.model.EmsStatus;

@Order
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

   @Override
   protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
       String error = "Malformed JSON request";
       return buildResponseEntity(new EmsStatus("BAD_REQUEST",400, error, null), HttpStatus.BAD_REQUEST);
   }
   
   @ExceptionHandler(Exception.class)
   protected ResponseEntity<Object> handleInternalServerError(
           Exception ex) {
	   String error = "Internal server error.";
       return buildResponseEntity(new EmsStatus("INTERNAL_SERVER_ERROR",500, error, null), HttpStatus.INTERNAL_SERVER_ERROR);

   }
   
  /* @ExceptionHandler(ResourceNotFoundException.class)
   protected ResponseEntity<Object> handleEntityNotFound(
		   ResourceNotFoundException ex) {
	   String error = "Entity not found..";
       return buildResponseEntity(new EmsStatus("NOT_FOUND",404, error, null), HttpStatus.NOT_FOUND);

   }*/

   private ResponseEntity<Object> buildResponseEntity(EmsStatus emsStatus, HttpStatus httpStatus) {
	   EmsResponse<Object> emsResponse = new EmsResponse();
	   emsResponse.setEmsStatus(emsStatus);
       return new ResponseEntity<>( emsResponse,  httpStatus);
   }

 
  

}
