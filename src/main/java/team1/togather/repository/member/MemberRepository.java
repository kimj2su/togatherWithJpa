package team1.togather.repository.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import team1.togather.domain.member.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Integer countByUserId(String userId);

    @Query("select m from Member m inner join fetch m.memberRoles where m.email= :email and m.provider is null")
    Member findByEmailAndProvider(String email);

    @Query("select distinct m from Member m join fetch m.memberRoles where m.username= :username")
    Member findByUsername(@Param("username")String username);

    @Query("select distinct m from Member m join fetch m.memberRoles where m.userId= :userId")
    Optional<Member> findByUserId(@Param("userId") String userId);


    @Query("select m from Member m join fetch m.memberRoles where m.id= :memberId")
    Member findByMember_Id(@Param("memberId")Long memberId);

    @Override
    @EntityGraph(attributePaths = {"memberRoles"})
    Page<Member> findAll(Pageable pageable);
}
