package prj1.enums;

public enum Promotion {
    TOOLS("Tạo mới ", "0"),
    TYPE("Bổ sung", "1");
    private final String status;
    private final String value;

    Promotion(String status, String value) {
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