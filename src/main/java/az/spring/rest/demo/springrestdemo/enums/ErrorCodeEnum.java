
package az.spring.rest.demo.springrestdemo.enums;

public enum ErrorCodeEnum {
    EMPLOYEE_NOT_FIND(1001,"can not find employee"),
    VALIDATION_ERROR(1002," is invalid"),
    NOT_VALID_VALUE(1003," would not be null"),
    Unknown_Error(1000," unknown error");

    private final int code;
    private final  String message;
    ErrorCodeEnum(int code,String message) {
        this.code=code;
        this.message=message;}

    public String getMessage() {
        return message;
    }
    public int getCode() {
        return code;
    }
}
