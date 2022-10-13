package team1.togather.controller.gathering;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import team1.togather.dto.*;
import team1.togather.dto.request.GatheringRequestDto;
import team1.togather.security.configs.SecurityConfig;
import team1.togather.security.configs.annotation.WithMember;
import team1.togather.service.gathering.GatheringService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.BDDMockito.then;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("View 컨트롤러 - 정모")
@WebMvcTest(controllers = GatheringController.class,
        excludeFilters = { //!Added!
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class) })
class GatheringControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private GatheringService gatheringService;

    @WithMockUser
    @DisplayName("[view][GET] 새 정모 개설 페이지")
    @Test
    void givenNothing_whenRequesting_thenReturnsNewGatheringPage() throws Exception {
        // Given
        Long groupTabId = 1L;
        // When & Then
        mvc.perform(get("/gatherings/new/" + groupTabId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("gatherings/createGatheringForm"));
    }

    @WithMockUser
    @DisplayName("[view][GET] 정모 상세 페이지")
    @Test
    void givenGatheringId_whenRequesting_thenReturnsGatheringPage() throws Exception {
        // Given
        Long gatheringId = 1L;
        given(gatheringService.getGathering(gatheringId)).willReturn(createGatheringDto());
        // When & Then
        mvc.perform(get("/gatherings/" + gatheringId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("gatherings/detail"));
    }

    @WithMockUser
    @DisplayName("[view][GET] 정모 장소 검색 페이지")
    @Test
    void givenGatheringPlace_whenRequesting_thenReturnsGatheringPlacePage() throws Exception {
        // Given
        String place = "서울";
        // When & Then
        mvc.perform(get("/gatherings/gatheringSearchMap")
                        .param("place", place))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("gatherings/searchMap"))
                .andExpect(model().attributeExists("place"));
    }

    @WithMember(value = "jisu1")
    @DisplayName("[view][POST] 일반 유저 새 정모 개설 - 정상 호출")
    @Test
    void givenBasicMemberAndNewGatheringInfo_whenRequesting_thenSavesNewGathering() throws Exception {
        // Given
        Long groupTabId = 1L;
        GatheringRequestDto gatheringRequestDto = createGatheringRequestDto();
        GatheringDto gatheringDto = createGatheringDto();

        given(gatheringService.saveGathering(gatheringDto)).willReturn(groupTabId);

        // When & Then
        mvc.perform(
                        post("/gatherings/new")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("username", gatheringRequestDto.getGaName())
                                .param("userId", gatheringRequestDto.getGaDate())
                                .param("password", gatheringRequestDto.getTime())
                                .param("email", gatheringRequestDto.getGaPlace())
                                .param("birth", gatheringRequestDto.getPrice())
                                .param("gaLimit", String.valueOf(gatheringRequestDto.getGaLimit()))
                                .param("groupTabId", String.valueOf(gatheringRequestDto.getGroupTabId()))
                                .with(csrf())
                ).andDo(print())
                .andExpect(status().is3xxRedirection());

        then(gatheringService).should().saveGathering(any(GatheringDto.class));
    }

    private GatheringRequestDto createGatheringRequestDto() {
        return GatheringRequestDto.of(
                "테스트 정모",
                "2022-08-25",
                "11-30",
                "서울",
                "20000",
                20,
                1L
        );
    }

    private GatheringDto createGatheringDto() {
        return GatheringDto.of(
                1L,
                "테스트 정모",
                "2022-08-25",
                "11-30",
                "서울",
                "20000",
                20,
                1L,
                createMemberDto()
        );
    }

    private MemberDto createMemberDto() {
        return MemberDto.of(
                1L,
                "jisu@email.com",
                "password",
                "jisu",
                "jisu1",
                "2022-08-11",
                "M",
                "category_first",
                "category_second",
                "category_third"
        );
    }
}