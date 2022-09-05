package team1.togather.domain.groupTab;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GroupUploadFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_upload_file_id")
    private Long id;

    @Embedded
    private UploadFile attachFile;

    public GroupUploadFile(UploadFile attachFile){
        this.attachFile = attachFile;
    }

    public static GroupUploadFile of(UploadFile attachFile) {
        return new GroupUploadFile(attachFile);
    }
}
