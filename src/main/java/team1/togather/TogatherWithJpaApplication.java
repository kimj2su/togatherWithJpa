package team1.togather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class TogatherWithJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TogatherWithJpaApplication.class, args); 
	}

}

// TODO: 2022-08-16 그룹 정보 까지 만들었고 그룹 crud 서비스 코드 작성함
// TODO: 2022-08-16 해야할것 : 그룹내 멤버 가입 , 인덱스페이지 관심사별, 관심사 가입 
// TODO: 2022-08-19 모임인원들 list에서 set으로 바꿔야함. 테스트코드 서비스부터 다시 짜기 
// TODO: 2022-08-21  모임 가입할때 request > toDto 로직 생각하기
// TODO: 2022-08-21 grade를 db에는 Long 넣고 꺼내올때는 String으로 꺼내와서 화면에 뿌려주기 그러면 뷰코드가 안바뀐다.
// TODO: 2022-09-01 그룹을 만들면 그룹아이디와 카테고리 아이디를 받아서 gc로 넣어서 찾아온다.
// TODO: 2022-09-08 gatheringInMember 엔티티 생성해야함. 
// TODO: 2022-09-10 챗룸만들기 버튼, 연관관계맺기 