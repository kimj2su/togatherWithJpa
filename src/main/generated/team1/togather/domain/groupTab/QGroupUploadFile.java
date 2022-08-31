package team1.togather.domain.groupTab;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGroupUploadFile is a Querydsl query type for GroupUploadFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGroupUploadFile extends EntityPathBase<GroupUploadFile> {

    private static final long serialVersionUID = 371004656L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGroupUploadFile groupUploadFile = new QGroupUploadFile("groupUploadFile");

    public final QUploadFile attachFile;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QGroupUploadFile(String variable) {
        this(GroupUploadFile.class, forVariable(variable), INITS);
    }

    public QGroupUploadFile(Path<? extends GroupUploadFile> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGroupUploadFile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGroupUploadFile(PathMetadata metadata, PathInits inits) {
        this(GroupUploadFile.class, metadata, inits);
    }

    public QGroupUploadFile(Class<? extends GroupUploadFile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.attachFile = inits.isInitialized("attachFile") ? new QUploadFile(forProperty("attachFile")) : null;
    }

}

