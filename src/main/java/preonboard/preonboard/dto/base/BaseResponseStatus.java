package preonboard.preonboard.dto.base;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {
    /**
     * 1000 : Successed
     */
    SUCCESS(true, 1000, "요청에 성공했습니다."),
    /**
     * 2xxx: Member
     */
    INVALID_EMAIL_FORMAT(false, 2000, "이메일 형식이 올바르지 않습니다."),

    INVALID_PASSWORD_FORMAT(false, 2001, "비밀번호는 8자 이상이어야 합니다.")

    ;



    private final boolean isSuccess;
    private final int code;
    private final String message;


    BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}