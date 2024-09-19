package prj1.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;


//import javax.servlet.http.HttpServletRequest;

@Slf4j
public class Utils {

  private Utils() {
    throw new IllegalStateException("Utils class");
  }

  public static String tryToWriteObjectAsJsonString(ObjectMapper objectMapper, Object value) {
    try {
      return objectMapper.writeValueAsString(value);
    } catch (JsonProcessingException e) {
      log.error(e.getMessage(), e);
      throw new IllegalArgumentException("Could not write object as json string");
    }
  }

  public static String appendLikeExpression(String value) {
    return String.format("%%%s%%", value);
  }

//  public static String getToken(HttpServletRequest request) {
//    return request.getHeader("Authorization").replace("Bearer", StringUtils.EMPTY);
//  }
}
