package team1.togather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import team1.togather.domain.member.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Integer countByNickname(String nickname);

    @Query("select m from Member m where m.email= :email and m.provider is null")
    Member findByEmailAndProvider(String email);

    Member findByPhone(String phone);

    Member findByUsername(String username);
}
