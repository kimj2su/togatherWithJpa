package team1.togather.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import team1.togather.config.file.FileStore;
import team1.togather.security.configs.TestSecurityConfig;
import team1.togather.service.GroupTabService;
import team1.togather.service.PaginationService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("Home 컨트롤러 테스트")
@Import(TestSecurityConfig.class)
@WebMvcTest(IndexController.class)
class IndexControllerTest {

    @Autowired
    private  MockMvc mvc;
    @MockBean
    private  GroupTabService groupTabService;
    @MockBean
    private  PaginationService paginationService;
    @MockBean
    private  FileStore fileStore;

//    public HomeControllerTest(@Autowired MockMvc mvc) {
//        this.mvc = mvc;
//    }

    @DisplayName("루트 페이지 테스트, 그룹 페이지 호출 ")
    @Test
    void givenNothing_whenRequestingIndexPage_thenRedirectsToIndexPage() throws Exception {
        System.out.println("mvc = " + mvc);
        // given
        given(groupTabService.indexGroupTabs(any(Pageable.class))).willReturn(Page.empty());
        given(paginationService.getPaginationBarNumbers(anyInt(), anyInt())).willReturn(List.of(0, 1, 2, 3, 4,5));

        // when & then
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("groupTabs"))
                .andExpect(model().attributeExists("paginationBarNumbers"))
                .andDo(MockMvcResultHandlers.print());
        then(groupTabService).should().indexGroupTabs(any(Pageable.class));
        then(paginationService).should().getPaginationBarNumbers(anyInt(), anyInt());

    }
}

