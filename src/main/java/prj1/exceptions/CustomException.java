package prj1.exceptions;

import java.util.List;
import java.util.Locale;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;

/**
 * Class BpmException kế thừa Exception phục vụ việc xử lý và tổng hợp lỗi trong sourceCode Bpm.
 *
 * @author KhanhNM
 * @since 13/02/2011
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CustomException extends RuntimeException {

  private String messageKey;
  private String message;
  private Throwable throwable;
  private List<String> messages;
  private HttpStatus status;

  public CustomException(String msgKey) {
    this.messageKey = msgKey;
    this.status = HttpStatus.BAD_REQUEST;
    this.message = ExceptionUtils.messages.get(msgKey);
  }


  public CustomException(String msgKey, HttpStatus status) {
    this.messageKey = msgKey;
    this.status = status;
  }

  public CustomException(String msgKey, String msg) {
    this.messageKey = msgKey;
    this.message = msg;
    this.status = HttpStatus.BAD_REQUEST;
  }

  public CustomException(String msgKey, String msg, HttpStatus status) {
    this.messageKey = msgKey;
    this.message = msg;
    this.status = status;
  }

  public String getLangMessage(MessageSource messageSource, String lang) {
    return messageSource.getMessage(this.messageKey, new Object[0], new Locale(lang));
  }
}
