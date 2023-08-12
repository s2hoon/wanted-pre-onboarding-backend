package preonboard.preonboard.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import preonboard.preonboard.dto.MemberJoinRequest;
import preonboard.preonboard.dto.MemberLoginRequest;
import preonboard.preonboard.dto.base.BaseException;
import preonboard.preonboard.dto.base.BaseResponse;
import preonboard.preonboard.dto.base.BaseResponseStatus;
import preonboard.preonboard.service.MemberService;

@RestController
@RequestMapping("/app/member")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MemberController {

    private final MemberService memberService;


    @PostMapping("/sign-up")
    public BaseResponse<String> join(@RequestBody MemberJoinRequest dto ) {
        try {
            memberService.join(dto.getEmail(), dto.getPassword());
            String result = "회원 가입 완료";
            return new BaseResponse<String>(BaseResponseStatus.SUCCESS, result);
        }
        // 이메일 형식 에러 & 비밀번호 형식 에러
        catch(BaseException e){
            return new BaseResponse<>(e.getStatus());
        }

    }


    @GetMapping("/sign-in")
    public BaseResponse<String> login(@RequestBody MemberLoginRequest dto) {

        try {
            String token = memberService.login(dto.getEmail(), dto.getPassword());
            return new BaseResponse<String>(BaseResponseStatus.SUCCESS,"Bearer " + token);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }

    }





}
