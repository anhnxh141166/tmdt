//package prj1.exceptions;
//
//import org.apache.tomcat.util.http.fileupload.FileUploadException;
//import org.apache.tomcat.util.http.fileupload.impl.InvalidContentTypeException;
//import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.multipart.MaxUploadSizeExceededException;
//import org.springframework.web.multipart.MultipartException;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.ConstraintViolationException;
//import javax.validation.constraints.NotNull;
//import java.time.format.DateTimeParseException;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//@ControllerAdvice
//class CustomizeExceptionAdvice {
//  @ExceptionHandler(MaxUploadSizeExceededException.class)
//  public ResponseEntity<Object> handleMaxSizeException(@NotNull MaxUploadSizeExceededException ex, HttpServletRequest request) {
//    LinkedHashMap<String, Object> body = new LinkedHashMap<>();
//    body.put("timestamp", new Date());
//    body.put("status", HttpStatus.BAD_REQUEST.value());
//    body.put("message", (ExceptionUtils.MAX_FILE_SIZE_UPLOAD));
//    return new ResponseEntity<>(body, HttpStatus.EXPECTATION_FAILED);
//  }
//
//  @ExceptionHandler(DateTimeParseException.class)
//  public ResponseEntity<Object> handleDateTimeParseException(@NotNull DateTimeParseException ex) {
//    LinkedHashMap<String, Object> body = appendBody(ex, HttpStatus.BAD_REQUEST, ex.getMessage());
//    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
//  }
//
//  @ExceptionHandler(FileUploadException.class)
//  public ResponseEntity<Object> handleInvalidContentTypeException(
//          @NotNull InvalidContentTypeException ex, HttpServletRequest request) {
//    LinkedHashMap<String, Object> body =
//        appendBody(ex, HttpStatus.BAD_REQUEST, "Body reaquest is required !");
//    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
//  }
//
//  @ExceptionHandler({
//    SizeLimitExceededException.class,
//  })
//  public ResponseEntity<Object> handleErrorSizeLimitExceededException(
//      @NotNull Exception exception) {
//    LinkedHashMap<String, Object> body =
//        appendBody(
//            exception,
//            HttpStatus.BAD_REQUEST,
//            (ExceptionUtils.MAX_FILE_SIZE_UPLOAD));
//    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
//  }
//
//  @ExceptionHandler({MultipartException.class})
//  public ResponseEntity<Object> handleErrorMultipartException(@NotNull Exception exception , HttpServletRequest request) {
//    String requestURI = request.getRequestURI();
//    String message;
//    if ("/ticket/create".equals(requestURI)) {
//      message = "All Required request part  is not present ";
//    } else {
//      message = "All Required request part  is not present";
//    }
//    LinkedHashMap<String, Object> body =
//            appendBody(exception, HttpStatus.BAD_REQUEST, message);
//    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
//  }
//
//  @ExceptionHandler({IllegalStateException.class})
//  public ResponseEntity<Object> handleErrorIllegalStateException(@NotNull Exception exception) {
//    LinkedHashMap<String, Object> body = appendBody(exception, HttpStatus.BAD_REQUEST, "3");
//    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
//  }
//
//  @ExceptionHandler(ConstraintViolationException.class)
//  public ResponseEntity<Object> handleConstrainViolationException(ConstraintViolationException exception) {
//    Map<String, String> response = new HashMap<>();
//    for (var e1 : exception.getConstraintViolations()) {
//      for (var e2 : e1.getPropertyPath()) {
//        response.put(e2.getName(), e1.getMessage());
//      }
//
//    }
//    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//  }
//
//  private LinkedHashMap<String, Object> appendBody(
//          Exception ex, HttpStatus status, String message) {
//    LinkedHashMap<String, Object> body = new LinkedHashMap<>();
//    body.put("timestamp", new Date());
//    body.put("status", status.value());
//    body.put("message", message);
//    return body;
//  }
//}
