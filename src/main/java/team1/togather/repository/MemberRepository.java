package team1.togather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import team1.togather.domain.member.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Integer countByUserId(String userId);

    @Query("select m from Member m inner join fetch m.memberRoles where m.email= :email and m.provider is null")
    Member findByEmailAndProvider(String email);

    Member findByUsername(String username);

    @Query("select count(m) from Member m where m.userId= :userId and m.provider is null")
    int countByUserIdAndProviderIsNull(String userId);

//    Member findByUserId(String userId);


    @Query("select distinct m from Member m join fetch m.memberRoles where m.userId= :userId")
    Optional<Member> findByUserId(String userId);
}
