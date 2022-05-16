package team1.togather.domain;

import java.sql.Date;
import java.time.LocalDateTime;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
public class GroupTab {

	@Id @GeneratedValue
	private Long gseq;
	private String gloc;
	private String gname;
	private String gintro;
	private String interest;
	private int limit;
	private LocalDateTime rdate;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "mnum")
	private Member member;
	private String fname;
//	private MultipartFile uploadFile;
	//== nameList, numberOfmem-in-group
//	private String mname;
//	private Long memInGroupCount;
//	private String int_out;
}
