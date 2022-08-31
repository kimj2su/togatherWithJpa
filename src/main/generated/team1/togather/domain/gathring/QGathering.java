package team1.togather.domain.gathring;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGathering is a Querydsl query type for Gathering
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGathering extends EntityPathBase<Gathering> {

    private static final long serialVersionUID = -619941277L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGathering gathering = new QGathering("gathering");

    public final StringPath gaDate = createString("gaDate");

    public final NumberPath<Integer> gaLimit = createNumber("gaLimit", Integer.class);

    public final StringPath gaName = createString("gaName");

    public final StringPath gaPlace = createString("gaPlace");

    public final team1.togather.domain.groupTab.QGroupTab groupTab;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final team1.togather.domain.member.QMember member;

    public final StringPath price = createString("price");

    public final StringPath time = createString("time");

    public QGathering(String variable) {
        this(Gathering.class, forVariable(variable), INITS);
    }

    public QGathering(Path<? extends Gathering> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGathering(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGathering(PathMetadata metadata, PathInits inits) {
        this(Gathering.class, metadata, inits);
    }

    public QGathering(Class<? extends Gathering> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.groupTab = inits.isInitialized("groupTab") ? new team1.togather.domain.groupTab.QGroupTab(forProperty("groupTab"), inits.get("groupTab")) : null;
        this.member = inits.isInitialized("member") ? new team1.togather.domain.member.QMember(forProperty("member")) : null;
    }

}

