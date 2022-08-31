package team1.togather.domain.member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRole is a Querydsl query type for Role
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRole extends EntityPathBase<Role> {

    private static final long serialVersionUID = -1711340418L;

    public static final QRole role = new QRole("role");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final SetPath<Member, QMember> members = this.<Member, QMember>createSet("members", Member.class, QMember.class, PathInits.DIRECT2);

    public final SetPath<Resources, QResources> resourcesSet = this.<Resources, QResources>createSet("resourcesSet", Resources.class, QResources.class, PathInits.DIRECT2);

    public final StringPath roleDesc = createString("roleDesc");

    public final StringPath roleName = createString("roleName");

    public QRole(String variable) {
        super(Role.class, forVariable(variable));
    }

    public QRole(Path<? extends Role> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRole(PathMetadata metadata) {
        super(Role.class, metadata);
    }

}

