package prj1.commons;

/**
 * Class định nghĩa các hằng số
 *
 * @author khanhnt
 * @since 1/12/2021
 */
public class Constants {

  public static final String COMMON_CATEGORY_COLUMN_CODE = "code";
  public static final String COMMON_CATEGORY_COLUMN_NAME = "name";
  public static final String COMMON_COLUMN_COMMON_CATEGORY_CODE = "commonCategoryCode";
    public static final String CLIENT_MESSAGE_ID = "clientMessageId";
  public static final String TRANSACTION_ID = "transactionId";

  public static final String HEADER_KEY_MSG_ID = "msgId";
  public static final String HEADER_KEY_CRE_DT_TM = "creDtTm";
  public static final String HEADER_KEY_FR_SYSTM = "frSystm";
  public static final String ROLE_MEMBER = "MEMBER";
  public static final String APPLICATION_PDF = "application/pdf";
  public static final String COMMA = ",";
  public static final String DEFAULT_FILE_TYPE = "NBU_FILE_TYPE_DEFAULT";
  public static final String DOT_COMMA = "; ";
  public static final String SLASHES = "/";
  public static final String DOT_DOT = ":";
  public static final String START_BASE64_STRING = "data:%s;base64";
  public static final String STRING_FORMAT_2_VARIABLE_WITH_UNDERLINED = "%s_%s";
  public static final String DTO_FORMAT = "dto%s";
  public static final String DEFAULT_DATE_FORMAT = "yyyy/MM/dd";
  public static final String VN_DATE_FORMAT = "dd/MM/yyyy";
  public static final String DIVISION = "/";
  public static final String DASH = "-";
  public static final String DATETIME_FORMAT_WITHOUT_SEPARATOR = "yyyyMMdd";
  public static final String VN_DATETIME_FORMAT_WITH_SEPARATOR = "HH:mm:ss dd/MM/yyyy";
  public static final String PDF = "PDF";
  private Constants() {
    throw new IllegalStateException("Constant class");
  }
}
