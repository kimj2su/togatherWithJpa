package team1.togather.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    private Long mnum;
    private String maddr;
    private String pfr_loc;
    private String mname;
    private String gender;
    private String birth;
    private String pwd;
    private String email;
    private String phone;
    private String category_first;
    private String category_second;
    private String category_third;
    private Long athur;
    private String sessionkey;
    private String sessionlimit;
}
