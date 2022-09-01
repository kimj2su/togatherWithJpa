package team1.togather.domain.groupTab;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGroupTabCategory is a Querydsl query type for GroupTabCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGroupTabCategory extends EntityPathBase<GroupTabCategory> {

    private static final long serialVersionUID = 1073000512L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGroupTabCategory groupTabCategory = new QGroupTabCategory("groupTabCategory");

    public final team1.togather.domain.member.QCategory category;

    public final QGroupTab groupTab;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QGroupTabCategory(String variable) {
        this(GroupTabCategory.class, forVariable(variable), INITS);
    }

    public QGroupTabCategory(Path<? extends GroupTabCategory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGroupTabCategory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGroupTabCategory(PathMetadata metadata, PathInits inits) {
        this(GroupTabCategory.class, metadata, inits);
    }

    public QGroupTabCategory(Class<? extends GroupTabCategory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new team1.togather.domain.member.QCategory(forProperty("category")) : null;
        this.groupTab = inits.isInitialized("groupTab") ? new QGroupTab(forProperty("groupTab"), inits.get("groupTab")) : null;
    }

}

