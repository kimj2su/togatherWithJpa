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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import team1.togather.config.file.FileStore;
import team1.togather.security.config.SecurityConfigTest;
import team1.togather.service.GroupTabService;
import team1.togather.service.PaginationService;
import team1.togather.util.FormDataEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("인덱스 컨트롤러 테스트")
@Import({SecurityConfigTest.class, FormDataEncoder.class})
@WebMvcTest(IndexController.class)
class IndexControllerTest {

    private final MockMvc mvc;
    private final FormDataEncoder formDataEncoder;
    @MockBean
    private GroupTabService groupTabService;
    @MockBean
    private PaginationService paginationService;

    @MockBean
    private FileStore fileStore;

    public IndexControllerTest(
            @Autowired MockMvc mvc,
            @Autowired FormDataEncoder formDataEncoder
    ) {
        this.mvc = mvc;
        this.formDataEncoder = formDataEncoder;
    }

    @DisplayName("루트 페이지 테스트, 그룹 페이지 호출 ")
    @Test
    void givenNothing_whenRequestingIndexPage_thenRedirectsToIndexPage() throws Exception {
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
