package team1.togather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team1.togather.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Integer countByNickname(String nickname);

    Member findByEmail(String email);

    Member findByPhone(String phone);
}
