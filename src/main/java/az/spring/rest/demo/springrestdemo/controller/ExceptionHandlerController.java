package az.spring.rest.demo.springrestdemo.controller;

import az.spring.rest.demo.springrestdemo.enums.ErrorCodeEnum;
import az.spring.rest.demo.springrestdemo.exception.CustomNotFoundException;
import az.spring.rest.demo.springrestdemo.rest.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ExceptionHandlerController {
   @ExceptionHandler(CustomNotFoundException.class)
   @ResponseStatus(HttpStatus.NOT_FOUND)
   public ErrorResponse handleCustomException(CustomNotFoundException e){
      return ErrorResponse.builder()
              .code(e.getCode())
              .message(e.getMessage())
              .build();

   }
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   @ExceptionHandler(MethodArgumentTypeMismatchException.class)
   public ErrorResponse handleInputParam( MethodArgumentTypeMismatchException e){
      String paramName=e.getParameter().getParameterName();
      return  ErrorResponse.builder()
              .message(paramName + ErrorCodeEnum.VALIDATION_ERROR.getMessage())
              .code(ErrorCodeEnum.VALIDATION_ERROR.getCode())
              .build();

   }

   @ResponseStatus(HttpStatus.BAD_REQUEST)
   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ErrorResponse handleValidation(MethodArgumentNotValidException e){
      String fieldName=e.getBindingResult().getFieldError().getField();
     return ErrorResponse.builder()
             .code(ErrorCodeEnum.NOT_VALID_VALUE.getCode())
             .message(fieldName+ErrorCodeEnum.NOT_VALID_VALUE.getMessage())
             .build();  }
   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
   @ExceptionHandler(Exception.class)
   public  ErrorResponse unknownException(Exception e){
      return ErrorResponse.builder()
              .message(ErrorCodeEnum.Unknown_Error.getMessage())
              .code(ErrorCodeEnum.Unknown_Error.getCode())
              .build(); }

}
