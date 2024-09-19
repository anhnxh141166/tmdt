//package prj1.exceptions;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.MissingServletRequestParameterException;
//import org.springframework.web.bind.ServletRequestBindingException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.multipart.support.MissingServletRequestPartException;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//@ControllerAdvice
//public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
//
//  // error handle for @Valid
//  @Override
//  protected ResponseEntity<Object> handleMethodArgumentNotValid(
//      MethodArgumentNotValidException ex,
//      HttpHeaders headers,
//      HttpStatus status,
//      WebRequest request) {
//    LinkedHashMap<String, Object> body = new LinkedHashMap<>();
//    body.put("timestamp", new Date());
//    body.put("status", status.value());
//
//    // Get all errors
//    Map<String, String> errors = new HashMap<>();
//
//    ex.getBindingResult().getFieldErrors().forEach(objectError -> errors.put(objectError.getField(), objectError.getDefaultMessage()));
//    body.put("errors", errors);
//    return new ResponseEntity<>(body, headers, status);
//  }
//
//  @Override
//  protected ResponseEntity<Object> handleHttpMessageNotReadable(
//      HttpMessageNotReadableException ex,
//      HttpHeaders headers,
//      HttpStatus status,
//      WebRequest request) {
//    return new ResponseEntity<>(appendBody(ex, status), headers, status);
//  }
//
//  @Override
//  protected ResponseEntity<Object> handleMissingServletRequestParameter(
//      MissingServletRequestParameterException ex,
//      HttpHeaders headers,
//      HttpStatus status,
//      WebRequest request) {
//    return new ResponseEntity<>(appendBody(ex, status), headers, status);
//  }
//
//  @Override
//  protected ResponseEntity<Object> handleMissingServletRequestPart(
//      MissingServletRequestPartException ex,
//      HttpHeaders headers,
//      HttpStatus status,
//      WebRequest request) {
//    return new ResponseEntity<>(appendBody(ex, status), headers, status);
//  }
//
//  @Override
//  protected ResponseEntity<Object> handleServletRequestBindingException(
//      ServletRequestBindingException ex,
//      HttpHeaders headers,
//      HttpStatus status,
//      WebRequest request) {
//    return new ResponseEntity<>(appendBody(ex, status), headers, status);
//  }
//
//  private LinkedHashMap<String, Object> appendBody(Exception ex, HttpStatus status) {
//    LinkedHashMap<String, Object> body = new LinkedHashMap<>();
//    body.put("timestamp", new Date());
//    body.put("status", status.value());
//    body.put("message", ex.getMessage());
//    return body;
//  }
//}
