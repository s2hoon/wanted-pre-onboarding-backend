package preonboard.preonboard.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import preonboard.preonboard.domain.Member;
import preonboard.preonboard.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


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

    private void validateEmail(String email) {
        if (!email.contains("@")) {
            throw new InvalidEmailFormatException("이메일 형식이 올바르지 않습니다.");
        }
    }

    private void validatePassword(String password) {
        if (password.length() < 8) {
            throw new InvalidPasswordException("비밀번호는 최소 8자 이상이어야 합니다.");
        }
    }

    public class InvalidEmailFormatException extends RuntimeException {
        public InvalidEmailFormatException(String message) {
            super(message);
        }
    }

    public class InvalidPasswordException extends RuntimeException {
        public InvalidPasswordException(String message) {
            super(message);
        }
    }


}
