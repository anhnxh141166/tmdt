package prj1.exceptions;

import java.util.HashMap;
import java.util.Map;

public class ExceptionUtils {
  public static final String ITEM_NOT_EXIST = "ITEM_NOT_EXIST";
  public static final String INVALID_FIELD_VALUE = "INVALID_FIELD_VALUE";
  public static final String E_INTERNAL_SERVER = "E_INTERNAL_SERVER";
  public static final String E_COMMON_DUPLICATE_CODE = "E_COMMON_DUPLICATE_CODE";
  public static final String E_COMMON_NOT_EXISTS_CODE = "E_COMMON_NOT_EXISTS_CODE";
  public static final String E_NOT_MATCH_CODE = "E_NOT_MATCH_CODE";
  public static final String COMMON_NULL_STRING = "COMMON_NULL_STRING";
  public static final String COMMON_CATEGORY_DUPLICATE_CODE = "COMMON_CATEGORY_DUPLICATE_CODE";
  public static final String COMMON_DUPLICATE_CODE = "COMMON_DUPLICATE_CODE";
  public static final String COMMON_ONLY_ONE_DEFAULT = "COMMON_ONLY_ONE_DEFAULT";
  public static final String COMMON_LESS_THAN_0 = "COMMON_LESS_THAN_0";
  public static final String COMMON_POSITION = "COMMON_POSITION";
  public static final String COMMON_NOT_EXIST = "COMMON_NOT_EXIST";

  public static final String COMMON_EXIST = "COMMON_EXIST";
  public static final String COMMON_DELETED = "COMMON_DELETED";
  public static final String E_EXPORT_001 = "E_EXPORT_001";
  public static final String ABILITY_NULL_STRING = "ABILITY_NULL_STRING";
  public static final String ABILITY_EXIST = "ABILITY_EXIST";
  public static final String ABILITY_NOT_EXIST = "ABILITY_NOT_EXIST";
  public static final String ABILITY_LEVEL = "ABILITY_LEVEL";
  public static final String ABILITY_EXIST_GROUP = "ABILITY_EXIST_GROUP";
  public static final String ABILITY_EXIST_CLASSIFY = "ABILITY_EXIST_CLASSIFY";
  public static final String ABILITY_INVALID_SIZE = "ABILITY_INVALID_SIZE";
  public static final String ABILITY_NULL_LEVEL_REQUIRED = "ABILITY_NULL_LEVEL_REQUIRED";
  public static final String LEVEL_UNIQUE_CODE = "LEVEL_UNIQUE_CODE";
  public static final String E_BODY_INFO_MISSING = "E_BODY_INFO_MISSING";
  public static final String PERMISSION_DENIED = "PERMISSION_DENIED";
  public static final String PROCESS_ALREADY_END = "PROCESS_ALREADY_END";
  public static final String FORBIDDEN = "FORBIDDEN";
  public static final String INVALID_POLICY_NUMBER = "INVALID_POLICY_NUMBER";
  public static final String GREATER_THAN_21_DAY = "GREATER_THAN_21_DAY";
  public static final String SAP_NOT_RESPONSE = "SAP_NOT_RESPONSE";
  public static final String REQUEST_TYPE_NOT_EXIST = "REQUEST_TYPE_NOT_EXIST";
  public static final String NUM_OF_FILES_AND_TYPES_NOT_EQUAL = "NUM_OF_FILES_AND_TYPES_NOT_EQUAL";
  public static final String INVALID_FILE_TYPES_STRING_REQUEST =
      "INVALID_FILE_TYPES_STRING_REQUEST";
  public static final String REQUEST_NOT_EXIST = "REQUEST_NOT_EXIST";
  public static final String INVALID_FILE_TYPE_UPLOAD = "INVALID_FILE_TYPE_UPLOAD";
  public static final String MISSING_REQUIRED_FILE = "MISSING_REQUIRED_FILE";
  public static final String MAX_FILE_SIZE_UPLOAD = "MAX_FILE_SIZE_UPLOAD";
  public static final String REVERSAL_FAIL = "REVERSAL_FAIL";
  public static final String SEARCH_PARAM_VALUE_NOT_MATCH_ANY_ENUM_VALUE =
      "SEARCH_PARAM_VALUE_NOT_MATCH_ANY_ENUM_VALUE";
  public static final String USER_NOT_EXIST = "USER_NOT_EXIST";
  public static final String INVALID_POLICY_NUMBER_NO_FLP_DATE =
      "INVALID_POLICY_NUMBER_NO_FLP_DATE";
  public static final String INVALID_POLICY_NUMBER_NO_POLICY_NUMBER_RETURN =
      "INVALID_POLICY_NUMBER_NO_POLICY_NUMBER_RETURN";
  public static final String POLICY_NUMBER_REQUEST_EXIST = "POLICY_NUMBER_REQUEST_EXIST";
  public static final String TOKEN_EXPIRES = "TOKEN_EXPIRES";
  public static final String CAN_NOT_FORWARD_TO_YOURSELF = "CAN_NOT_FORWARD_TO_YOURSELF";
  public static final String CAN_NOT_FORWARD = "CAN_NOT_FORWARD";
  public static final String ATTACHMENT_NOT_EXIST = "ATTACHMENT_NOT_EXIST";
  public static final String EXPORT_REPORT_FAIL = "EXPORT_REPORT_FAIL";
  public static final String CREATE_PAYMENT_REQUEST_FAIL = "CREATE_PAYMENT_REQUEST_FAIL";
  public static final String NOT_CHOSEN_OR_ADD_NEW_BANK_ACCOUNT_YET =
      "NOT_CHOSEN_OR_ADD_NEW_BANK_ACCOUNT_YET";
  public static final String CREATE_PAYMENT_RUN_FAIL = "CREATE_PAYMENT_RUN_FAIL";
  public static final String UPLOAD_ATTACHMENT_TO_SAP_FAIL = "UPLOAD_ATTACHMENT_TO_SAP_FAIL";
  public static final String NO_SEARCH_RESULT_FOUND = "NO_SEARCH_RESULT_FOUND";
  public static final String BANK_ACCOUNT_HAD_EXIST = "BANK_ACCOUNT_HAD_EXIST";
  public static final String MEDICAL_FEE_CANNOT_GREATER_THAN_POA_AND_PREMIUM_AMOUNT =
      "MEDICAL_FEE_CANNOT_GREATER_THAN_POA_AND_PREMIUM_AMOUNT";
  public static final String FILTER_CAN_NOT_NULL = "FILTER_CAN_NOT_NULL";
  public static final String USED_TO_PAYMENT_RUN = "USED_TO_PAYMENT_RUN";
  public static final String CREATE_PAYMENT_RUN_FAIL_RESPONSE_LIST_DOC_NULL =
      "CREATE_PAYMENT_RUN_FAIL_RESPONSE_LIST_DOC_NULL";
  public static final String PAYMENT_RUN_RESPONSE_DO_NOT_CONTAIN_DOC_11 =
      "PAYMENT_RUN_RESPONSE_DO_NOT_CONTAIN_DOC_11";
  public static final String INVALID_INPUT_BANK_ACCOUNT_NUMBER =
      "INVALID_INPUT_BANK_ACCOUNT_NUMBER";
  public static final String BANK_ACCOUNT_DOES_NOT_EXIST_OP_SAP =
      "BANK_ACCOUNT_DOES_NOT_EXIST_OP_SAP";
  public static final String APPLY_NEW_RULE_FAIL = "APPLY_NEW_RULE_FAIL";
  public static final String BPM_COMPLETE_TASK_WITH_NO_VARIABLES =
      "BPM_COMPLETE_TASK_WITH_NO_VARIABLES";
  public static final String TEST_MULTI_LANG_MESSAGE = "TEST_MULTI_LANG_MESSAGE";
  public static final String ADD_NEW_BANK_FAIL = "ADD_NEW_BANK_FAIL";
  public static final String NOT_PDF = "FILE_IS_NOT_PDF";
  public static final String FILE_IS_EMPTY = "FILE_IS_EMPTY";
  public static final String DECISION_MUST_BE_APPROVED_BEFORE_PROCESSING =
      "DECISION_MUST_BE_APPROVED_BEFORE_PROCESSING";
  public static final String REQUEST_IS_NOT_REQUIRED_ADDITIONAL_INFORMATION =
      "REQUEST_IS_NOT_REQUIRED_ADDITIONAL_INFORMATION";
  public static final String PLEASE_UPLOAD_FILE_ATTACHMENT = "PLEASE_UPLOAD_FILE_ATTACHMENT";
  public static final String INVALID_VALUE_VALIDATED = "INVALID_VALUE_VALIDATED";
  public static final String SIZE_GROUPPRODUCT = "SIZE_NOT_EQUAL";
  public static final String SIZE_GROUPPRODUCT_AND_DETAILPROMOTION = "SIZE_GROUPPRODUCT_AND_DETAILPROMOTION";
  public static final String NOT_FIND_GROUPPRODUCT = "NOT_FIND_GROUPPRODUCT";
  public static final String FIND_PROMOOTION = "FIND_PROMOOTION";
  public static final String NOT_FIND_PROMOOTION = "NOT_FIND_PROMOOTION";
  public static final String NOT_FIND_DETAILPROMOTION = "NOT_FIND_DETAILPROMOTION";
  public static final Map<String, String> messages;

  static {
    messages = new HashMap<>();
    messages.put(SIZE_GROUPPRODUCT, "Một số group product không tồn tại");
    messages.put(SIZE_GROUPPRODUCT_AND_DETAILPROMOTION, "Một số group product không tồn tại hoặc detail promotion không tồn tại");
    messages.put(FIND_PROMOOTION, "Promotion đã tồn tại");
    messages.put(NOT_FIND_PROMOOTION, "Promotion không tồn tại");
    messages.put(NOT_FIND_GROUPPRODUCT, "Group product không tồn tại");
    messages.put(NOT_FIND_DETAILPROMOTION, "Detail promotion không tồn tại");
    messages.put(E_INTERNAL_SERVER, "Server không phản hồi");
    messages.put(COMMON_NULL_STRING, "Yêu cầu không được để trống");
    messages.put(COMMON_DUPLICATE_CODE, "Mã danh mục con là unique");
    messages.put(COMMON_CATEGORY_DUPLICATE_CODE, "Mã danh mục cha là unique");
    messages.put(COMMON_NOT_EXIST, "Danh mục không tồn tại");
    messages.put(COMMON_EXIST, "Danh mục đã tồn tại");
    messages.put(E_EXPORT_001, "Lỗi xuất excel");
    messages.put(COMMON_LESS_THAN_0, "Số thứ tự không được nhỏ hơn 0");
    messages.put(COMMON_POSITION, "Vị trí phải khác nhau");
    messages.put(COMMON_DELETED, "Bản ghi đã xóa");
    messages.put(COMMON_ONLY_ONE_DEFAULT, "Chỉ được 1 giá trị default");
    messages.put(
        ABILITY_NULL_STRING,
        "Không chấp nhận giá trị null code,name,capacity group code,classify code,max level");
    messages.put(ABILITY_EXIST, "Năng lực đã tồn tại");
    messages.put(ABILITY_NOT_EXIST, "Năng lực không tồn tại");
    messages.put(ABILITY_LEVEL, "3=<LEVEL<=5");
    messages.put(ABILITY_EXIST_GROUP, "Nhóm năng lực không tồn tại");
    messages.put(ABILITY_EXIST_CLASSIFY, "Loại năng lực không tồn tại");
    messages.put(ABILITY_INVALID_SIZE, "Danh sách cấp bậc không hợp lệ");
    messages.put(ABILITY_NULL_LEVEL_REQUIRED, "Code and name required not null");
    messages.put(LEVEL_UNIQUE_CODE, "Level code required unique");
    messages.put(ExceptionUtils.E_BODY_INFO_MISSING, "Không nhận được RequestBody");
    messages.put(ExceptionUtils.E_COMMON_DUPLICATE_CODE, "Code %s đã tồn tại");
    messages.put(ExceptionUtils.E_COMMON_NOT_EXISTS_CODE, "Code %s không tồn tại");
    messages.put(ExceptionUtils.FORBIDDEN, "Không có quyền truy cập");
    messages.put(
        ExceptionUtils.INVALID_POLICY_NUMBER, "Số hợp đồng ko tồn tại hoặc thiếu thông tin.");
    messages.put(ExceptionUtils.GREATER_THAN_21_DAY, "Hợp đồng đã quá 21 ngày đê hủy.");
    messages.put(ExceptionUtils.SAP_NOT_RESPONSE, "SAP không phảin hồi");
    messages.put(ExceptionUtils.REQUEST_TYPE_NOT_EXIST, "Loại yêu cầu không tồn tại");
    messages.put(
        ExceptionUtils.NUM_OF_FILES_AND_TYPES_NOT_EQUAL,
        "Số lượng file và loại file đẩy lên phải bằng nhau !  ");
    messages.put(
        ExceptionUtils.INVALID_FILE_TYPES_STRING_REQUEST,
        "Chuỗi loại file không hợp lệ ! types : invalid  ");
    messages.put(ExceptionUtils.REQUEST_NOT_EXIST, "Yêu cầu không tồn tại.  ");
    messages.put(
        ExceptionUtils.INVALID_FILE_TYPE_UPLOAD,
        "Chỉ chấp nhận file pdf và image (ngoại trừ image/webp)");
    messages.put(ExceptionUtils.MISSING_REQUIRED_FILE, "Upload file ZSAP_CA bắt buộc ");
    messages.put(ExceptionUtils.MAX_FILE_SIZE_UPLOAD, "Chỉ được upload file nhỏ hơn <=10 MB  ");
    messages.put(
        ExceptionUtils.REVERSAL_FAIL,
        "Reversal thất bại . Số hợp dồng không tồn tại hoặc đã bị reversal . %s ");
    messages.put(
        ExceptionUtils.SEARCH_PARAM_VALUE_NOT_MATCH_ANY_ENUM_VALUE,
        " Gía trị tìm kiếm  %s  không tồn tại trong hệ thống !  ");
    messages.put(ExceptionUtils.USER_NOT_EXIST, " User name  %s  không tồn tại trong hệ thống !  ");
    messages.put(
        ExceptionUtils.INVALID_POLICY_NUMBER_NO_FLP_DATE,
        " Số hợp đồng không hơp lệ : ko có FLP_DT !  ");
    messages.put(
        ExceptionUtils.INVALID_POLICY_NUMBER_NO_POLICY_NUMBER_RETURN,
        " Số hợp đồng không hơp lệ : ko có policy number trả về hoặc hợp đồng đã revered !  ");
    messages.put(
        ExceptionUtils.POLICY_NUMBER_REQUEST_EXIST,
        "Yêu cầu hủy của số hợp đồng %s đã tồn tại .  ");
    messages.put(ExceptionUtils.TOKEN_EXPIRES, "MB bank token đã hết hạn ! ");
    messages.put(ExceptionUtils.CAN_NOT_FORWARD_TO_YOURSELF, "Tự forward cho chính mình  ???! ");
    messages.put(ExceptionUtils.CAN_NOT_FORWARD, "Yêu cầu không phải của bạn không thể forwwad . ");
    messages.put(ExceptionUtils.ATTACHMENT_NOT_EXIST, "AttachmentType ko tồn tại. ");
    messages.put(ExceptionUtils.EXPORT_REPORT_FAIL, "EXPORT_REPORT_FAIL ");
    messages.put(ExceptionUtils.CREATE_PAYMENT_REQUEST_FAIL, "CREATE_PAYMENT_REQUEST_FAIL ");
    messages.put(
        ExceptionUtils.NOT_CHOSEN_OR_ADD_NEW_BANK_ACCOUNT_YET,
        "CHưa chọn tài khoản ngân hàng hoặc thêm thất bại");
    messages.put(ExceptionUtils.CREATE_PAYMENT_RUN_FAIL, "tạo payment run thất bại");
    messages.put(ExceptionUtils.UPLOAD_ATTACHMENT_TO_SAP_FAIL, "Upload file lên sap thất bại");
    messages.put(ExceptionUtils.NO_SEARCH_RESULT_FOUND, "Không tìm thấy kết quả");
    messages.put(ExceptionUtils.BANK_ACCOUNT_HAD_EXIST, "Tài khoản ngân hàng đã tồn tại");
    messages.put(
        ExceptionUtils.MEDICAL_FEE_CANNOT_GREATER_THAN_POA_AND_PREMIUM_AMOUNT,
        "Phí khám lớn hơn POA và premium account");
    messages.put(ExceptionUtils.FILTER_CAN_NOT_NULL, "Vui lòng chọn yêu cầu tìm kiếm");
    messages.put(ExceptionUtils.USED_TO_PAYMENT_RUN, "Đã từng payment run, không thế chạy lại");
    messages.put(
        ExceptionUtils.CREATE_PAYMENT_RUN_FAIL_RESPONSE_LIST_DOC_NULL,
        "tạo payment run thất bại danh sách payment doc trả về rỗng");
    messages.put(
        ExceptionUtils.PAYMENT_RUN_RESPONSE_DO_NOT_CONTAIN_DOC_11,
        "PAYMENT RUN RESPONSE DO NOT CONTAIN DOC 11");
    messages.put(ExceptionUtils.INVALID_INPUT_BANK_ACCOUNT_NUMBER, "Account doesn't exist");
    messages.put(
        ExceptionUtils.BANK_ACCOUNT_DOES_NOT_EXIST_OP_SAP, "Tài khoản không tồn tại trên sap");
    messages.put(ExceptionUtils.APPLY_NEW_RULE_FAIL, "APPLY NEW RULE FAIL");
    messages.put(
        ExceptionUtils.BPM_COMPLETE_TASK_WITH_NO_VARIABLES,
        "Chưa đẩy biến vào process khi complete userTask");
    messages.put(ExceptionUtils.NOT_PDF, "Tệp gửi lên không phải PDF");
    messages.put(ExceptionUtils.FILE_IS_EMPTY, "Tệp gửi lên không có dữ liệu hoặc không tồn tại!");

    messages.put(ExceptionUtils.ITEM_NOT_EXIST, "Không tồn tại");
    messages.put(ExceptionUtils.INVALID_FIELD_VALUE, "GIá trị nhập không hợp lệ");
  }

  private ExceptionUtils() {
  }
}
