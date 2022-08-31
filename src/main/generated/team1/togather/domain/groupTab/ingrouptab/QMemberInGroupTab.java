package team1.togather.domain.groupTab.ingrouptab;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberInGroupTab is a Querydsl query type for MemberInGroupTab
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberInGroupTab extends EntityPathBase<MemberInGroupTab> {

    private static final long serialVersionUID = 536921084L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberInGroupTab memberInGroupTab = new QMemberInGroupTab("memberInGroupTab");

    public final team1.togather.domain.QAuditingFields _super = new team1.togather.domain.QAuditingFields(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final EnumPath<MemberGrade> grade = createEnum("grade", MemberGrade.class);

    public final team1.togather.domain.groupTab.QGroupTab groupTab;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final team1.togather.domain.member.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    public QMemberInGroupTab(String variable) {
        this(MemberInGroupTab.class, forVariable(variable), INITS);
    }

    public QMemberInGroupTab(Path<? extends MemberInGroupTab> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberInGroupTab(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberInGroupTab(PathMetadata metadata, PathInits inits) {
        this(MemberInGroupTab.class, metadata, inits);
    }

    public QMemberInGroupTab(Class<? extends MemberInGroupTab> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.groupTab = inits.isInitialized("groupTab") ? new team1.togather.domain.groupTab.QGroupTab(forProperty("groupTab"), inits.get("groupTab")) : null;
        this.member = inits.isInitialized("member") ? new team1.togather.domain.member.QMember(forProperty("member")) : null;
    }

}

