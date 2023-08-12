package preonboard.preonboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import preonboard.preonboard.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
