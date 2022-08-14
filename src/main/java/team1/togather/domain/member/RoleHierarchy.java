package team1.togather.domain.member;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="ROLE_HIERARCHY")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString(exclude = {"parentName", "roleHierarchy"})
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class RoleHierarchy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "child_name")
    private String childName;

    @ManyToOne(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_name", referencedColumnName = "child_name")
    private RoleHierarchy parentName;

    @OneToMany(mappedBy = "parentName", cascade={CascadeType.ALL})
    private Set<RoleHierarchy> roleHierarchy = new HashSet<RoleHierarchy>();
}
