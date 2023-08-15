package preonboard.preonboard.dto.base;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {
    /**
     * 1000 : Success
     */
    SUCCESS(true, 1000, "요청에 성공했습니다."),
    /**
     * 2xxx: Member
     */
    INVALID_EMAIL_FORMAT(false, 2000, "이메일 형식이 올바르지 않습니다."),

    INVALID_PASSWORD_FORMAT(false, 2001, "비밀번호는 8자 이상이어야 합니다."),

    WRONG_EMAIL(false, 2002, "이메일이 올바르지 않습니다."),
    WRONG_PASSWORD(false, 2003, "비밀번호가 올바르지 않습니다."),
    AUTHENTICATE_FAILED(false, 2004, "인증 실패."),
    NO_THAT_MEMBER(false, 2005, "id에 해당하는 유저가 없습니다."),
    /**
     * 3xxx: Post
     */
    WRITE_POST_FAILED(false, 3000, "게시글 작성에 실패하였습니다."),

    NO_THAT_ID_POST(false, 3001, "id에 해당하는 게시글이 없습니다."),
    
    FORBIDDEN(false, 3003, "권한이 없습니다."),;



    private final boolean isSuccess;
    private final int code;
    private final String message;


    BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}