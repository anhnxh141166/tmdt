package prj1.enums;

public enum SubmitTypeEnum {
  FIRST_SUBMIT("Tạo mới ", "0"),
  ADDITIONAL("Bổ sung", "1");
  private final String status;
  private final String value;

  SubmitTypeEnum(String status, String value) {
    this.status = status;
    this.value = value;
  }

  public String getStatus() {
    return status;
  }

  public String getValue() {
    return value;
  }
}
