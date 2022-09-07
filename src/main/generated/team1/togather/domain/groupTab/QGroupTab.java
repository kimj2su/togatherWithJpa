package team1.togather.domain.groupTab;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGroupTab is a Querydsl query type for GroupTab
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGroupTab extends EntityPathBase<GroupTab> {

    private static final long serialVersionUID = -1704865502L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGroupTab groupTab = new QGroupTab("groupTab");

    public final team1.togather.domain.QAuditingFields _super = new team1.togather.domain.QAuditingFields(this);

    public final team1.togather.domain.member.QCategory category;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final SetPath<team1.togather.domain.gathring.Gathering, team1.togather.domain.gathring.QGathering> gathering = this.<team1.togather.domain.gathring.Gathering, team1.togather.domain.gathring.QGathering>createSet("gathering", team1.togather.domain.gathring.Gathering.class, team1.togather.domain.gathring.QGathering.class, PathInits.DIRECT2);

    public final StringPath groupIntro = createString("groupIntro");

    public final StringPath groupLocation = createString("groupLocation");

    public final StringPath groupName = createString("groupName");

    public final QGroupUploadFile groupUploadFile;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath interest = createString("interest");

    public final team1.togather.domain.member.QMember member;

    public final NumberPath<Integer> memberLimit = createNumber("memberLimit", Integer.class);

    public final SetPath<team1.togather.domain.groupTab.ingrouptab.MemberInGroupTab, team1.togather.domain.groupTab.ingrouptab.QMemberInGroupTab> membersInGroupTab = this.<team1.togather.domain.groupTab.ingrouptab.MemberInGroupTab, team1.togather.domain.groupTab.ingrouptab.QMemberInGroupTab>createSet("membersInGroupTab", team1.togather.domain.groupTab.ingrouptab.MemberInGroupTab.class, team1.togather.domain.groupTab.ingrouptab.QMemberInGroupTab.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    public final StringPath userId = createString("userId");

    public QGroupTab(String variable) {
        this(GroupTab.class, forVariable(variable), INITS);
    }

    public QGroupTab(Path<? extends GroupTab> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGroupTab(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGroupTab(PathMetadata metadata, PathInits inits) {
        this(GroupTab.class, metadata, inits);
    }

    public QGroupTab(Class<? extends GroupTab> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new team1.togather.domain.member.QCategory(forProperty("category")) : null;
        this.groupUploadFile = inits.isInitialized("groupUploadFile") ? new QGroupUploadFile(forProperty("groupUploadFile"), inits.get("groupUploadFile")) : null;
        this.member = inits.isInitialized("member") ? new team1.togather.domain.member.QMember(forProperty("member")) : null;
    }

}

