package team1.togather.domain.member;

import lombok.*;
import team1.togather.domain.member.Member;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ROLE")
@Getter
@Setter
@ToString(exclude = {"users"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Role implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_desc")
    private String roleDesc;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "memberRoles")
    private Set<Member> members = new HashSet<>();

}