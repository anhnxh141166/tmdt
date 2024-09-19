package prj1.models.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
//import javax.servlet.http.HttpServletRequest;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import prj1.commons.Constants;
import prj1.exceptions.ExceptionUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseData<T> implements Serializable {

  private static final long serialVersionUID = 1L;

  private  String timestamp;

  private  String clientMessageId;

  private  String transactionId;

  private  String msgId;
  private  String creDtTm;
  private  String frSystm;
  private  String path;
  private int code;
  private String message;
  private String messageKey;
  private int status;
  private transient T data;

  public ResponseData(
      String clientMessageId,
      String transactionId,
      String msgId,
      String creDtTm,
      String frSystm,
      String path) {
    this.code = 0;
    this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    this.message = "Successful!";
    this.clientMessageId = clientMessageId;
    this.transactionId = transactionId;
    this.msgId = msgId;
    this.creDtTm = creDtTm;
    this.frSystm = frSystm;
    this.path = path;
    this.status = 200;
  }

   

    public ResponseData(
            ServerHttpRequest request) {
      HttpHeaders headers = request.getHeaders();
//    this.code = 0;
    this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
//    this.message = "Successful!";
//    this.clientMessageId = headers.get(Constants.CLIENT_MESSAGE_ID);
//    this.transactionId = headers.get(Constants.TRANSACTION_ID);
//    this.msgId = headers.get(Constants.HEADER_KEY_MSG_ID);
//    this.creDtTm = headers.get(Constants.HEADER_KEY_CRE_DT_TM);
//    this.frSystm = headers.get(Constants.HEADER_KEY_FR_SYSTM);
    this.path = request.getURI().getPath();
//    this.status = 200;
  }

//  public ResponseData(
//      HttpServletRequest request) {
////    this.code = 0;
//    this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
////    this.message = "Successful!";
//    this.clientMessageId = request.getHeader(Constants.CLIENT_MESSAGE_ID);
//    this.transactionId = request.getHeader(Constants.TRANSACTION_ID);
//    this.msgId = request.getHeader(Constants.HEADER_KEY_MSG_ID);
//    this.creDtTm = request.getHeader(Constants.HEADER_KEY_CRE_DT_TM);
//    this.frSystm = request.getHeader(Constants.HEADER_KEY_FR_SYSTM);
//    this.path = request.getRequestURI();
////    this.status = 200;
//  }

  public ResponseData<T> success(T data) {
    this.data = data;
    return this;
  }
  public ResponseData<T> success(T data,String mesasage) {
    this.data = data;
    this.message =mesasage;
    return this;
  }

  public ResponseData<T> error(int code, String message, String messageKey, int status) {
    this.code = code;
    this.messageKey = messageKey;
    this.message = message;
    this.status = status;
    return this;
  }

  public ResponseData<T> error(int code, String message, int status) {
    this.code = code;
    this.messageKey = ExceptionUtils.E_INTERNAL_SERVER;
    this.message = message;
    this.status = status;
    return this;
  }

  public ResponseData<T> error(int code, String message, String messageKey, T data, int status) {
    this.data = data;
    this.code = code;
    this.messageKey = messageKey;
    this.message = message;
    this.status = status;
    return this;
  }

  public ResponseData<T> error(int code, String message, T data, int status) {
    this.data = data;
    this.code = code;
    this.messageKey = ExceptionUtils.E_INTERNAL_SERVER;
    this.message = message;
    this.status = status;
    return this;
  }

  public void setData(T data) {
    this.data = data;
  }
}
