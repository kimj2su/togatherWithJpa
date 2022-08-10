package team1.togather.domain.group;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Embeddable //jpa의 내장 타입, 어딘가에 내장 될 수 있다.
@Data
public class UploadFile {

    private String uploadFileName;
    private String storeFileName;

    protected UploadFile() {}
    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }


}
