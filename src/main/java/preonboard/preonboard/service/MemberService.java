package preonboard.preonboard.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import preonboard.preonboard.config.JwtTokenUtil;
import preonboard.preonboard.domain.Member;
import preonboard.preonboard.dto.base.BaseException;
import preonboard.preonboard.dto.base.BaseResponseStatus;
import preonboard.preonboard.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${jwt.token.secret}")
    private String key;
    private Long expireTimeMs = 1000 * 60 * 60L; //1시간


    public void join(String email, String password) {
        //유효성 검사
        validateEmail(email);
        validatePassword(password);

        Member member = new Member();
        member.setEmail(email);
        String hashedPassword = bCryptPasswordEncoder.encode(password);
        member.setPassword(hashedPassword);
        memberRepository.save(member);

    }

    public String login(String email, String password){

        //유효성 검사
        validateEmail(email);
        validatePassword(password);

        //email 중복확인
        Member selectedUser = memberRepository.findByEmail(email)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.WRONG_EMAIL));

        //비밀번호 틀림
        if (!bCryptPasswordEncoder.matches(password, selectedUser.getPassword())) {
            throw new BaseException(BaseResponseStatus.WRONG_PASSWORD);
        }

        //이메일로 토큰 생성
        String token = JwtTokenUtil.createToken(selectedUser.getEmail(), key, expireTimeMs);

        return token;
    }
    private void validateEmail(String email) {
        if (!email.contains("@")) {
            throw new BaseException(BaseResponseStatus.INVALID_EMAIL_FORMAT);
        }
    }

    private void validatePassword(String password) {
        if (password.length() < 8) {
            throw new BaseException(BaseResponseStatus.INVALID_PASSWORD_FORMAT);
        }
    }






}
