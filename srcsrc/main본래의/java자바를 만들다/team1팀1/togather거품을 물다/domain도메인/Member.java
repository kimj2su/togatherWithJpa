<font class="papago-parent"><font class="papago-source" style="display:none;">package team1.togather.domain;
</font>패키지 팀1.togather.domain;</font><font class="papago-parent"><font class="papago-source" style="display:none;">
</font>
</font><font class="papago-parent"><font class="papago-source" style="display:none;">import lombok.</font>롬복을 수입하다</font><font class="papago-parent"><font class="papago-source" style="display:none;">Getter;
</font>게터;</font><font class="papago-parent"><font class="papago-source" style="display:none;">import lombok.</font>롬복을 수입하다</font><font class="papago-parent"><font class="papago-source" style="display:none;">Setter;
</font>세터;</font><font class="papago-parent"><font class="papago-source" style="display:none;">
</font>
</font><font class="papago-parent"><font class="papago-source" style="display:none;">import javax.persistence.</font>자바스.프랜드를 수입하다</font><font class="papago-parent"><font class="papago-source" style="display:none;">Entity;
</font>엔티티;</font><font class="papago-parent"><font class="papago-source" style="display:none;">import javax.persistence.</font>자바스.프랜드를 수입하다</font><font class="papago-parent"><font class="papago-source" style="display:none;">GeneratedValue;
</font>생성된 값;</font><font class="papago-parent"><font class="papago-source" style="display:none;">import javax.persistence.</font>자바스.프랜드를 수입하다</font><font class="papago-parent"><font class="papago-source" style="display:none;">Id;
</font>ID;</font><font class="papago-parent"><font class="papago-source" style="display:none;">
</font>
</font><font class="papago-parent"><font class="papago-source" style="display:none;">@Entity
</font>@엔티티</font><font class="papago-parent"><font class="papago-source" style="display:none;">@Getter @Setter
</font>@게터 @세터 </font><font class="papago-parent"><font class="papago-source" style="display:none;">public class Member {
</font>공용 클래스 멤버 {</font><font class="papago-parent"><font class="papago-source" style="display:none;">
</font>
</font><font class="papago-parent"><font class="papago-source" style="display:none;">    @Id @GeneratedValue
</font>@Id @GeneratedValue</font><font class="papago-parent"><font class="papago-source" style="display:none;">    private Long mnum;
</font>개인 롱 mnum;</font><font class="papago-parent"><font class="papago-source" style="display:none;">    private String maddr;
</font>개인용 문자열 매드르;</font><font class="papago-parent"><font class="papago-source" style="display:none;">    private String pfr_loc;
</font>개인 문자열 pfr_loc;</font><font class="papago-parent"><font class="papago-source" style="display:none;">    private String mname;
</font>개인 문자열 mname;</font><font class="papago-parent"><font class="papago-source" style="display:none;">    private String gender;
</font>개인 문자열 성별;</font><font class="papago-parent"><font class="papago-source" style="display:none;">    private String birth;
</font>개인 문자열 생성;</font><font class="papago-parent"><font class="papago-source" style="display:none;">    private String pwd;
</font>개인 문자열 pwd;</font><font class="papago-parent"><font class="papago-source" style="display:none;">    private String email;
</font>개인 문자열 이메일;</font><font class="papago-parent"><font class="papago-source" style="display:none;">    private String phone;
</font>개인용 문자열 전화;</font><font class="papago-parent"><font class="papago-source" style="display:none;">    private String category_first;
</font>개인 문자열 범주_first;</font><font class="papago-parent"><font class="papago-source" style="display:none;">    private String category_second;
</font>개인 문자열 범주_초;</font><font class="papago-parent"><font class="papago-source" style="display:none;">    private String category_third;
</font>개인 문자열 범주_3;</font><font class="papago-parent"><font class="papago-source" style="display:none;">    private Long athur;
</font>롱 애터 일병;</font><font class="papago-parent"><font class="papago-source" style="display:none;">    private String sessionkey;
</font>개인 문자열 세션키;</font><font class="papago-parent"><font class="papago-source" style="display:none;">    private String sessionlimit;
</font>개인 문자열 세션 제한;</font><font class="papago-parent"><font class="papago-source" style="display:none;">}
</font>}</font>
