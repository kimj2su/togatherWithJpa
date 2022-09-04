package team1.togather.controller.grouptab;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import team1.togather.security.configs.SecurityConfig;
import team1.togather.service.PaginationService;
import team1.togather.service.grouptab.GroupTabService;
import team1.togather.service.member.CategoryService;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@DisplayName("View 컨트롤러 - 그룹")
@WebMvcTest(controllers = SearchGroupTabsController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)})
class SearchGroupTabsControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private GroupTabService groupTabService;
    @MockBean
    private PaginationService paginationService;
    @MockBean
    private CategoryService categoryService;

    @WithMockUser
    @DisplayName("[view][GET] searchValue 없이 검색 페이지")
    @Test
    void givenNothing_whenRequesting_thenReturnsNewSearchGroupTabPage() throws Exception {
        // Given
        given(groupTabService.searchGroupTabs(eq(null), any(Pageable.class))).willReturn(Page.empty());
        given(paginationService.getPaginationBarNumbers(anyInt(), anyInt())).willReturn(List.of(0, 1, 2, 3, 4, 5));
        given(categoryService.getIntOut()).willReturn(List.of(""));
        // When & Then
        mvc.perform(get("/search"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("groupTabs/search-grouptab"))
                .andExpect(model().attributeExists("groupTabs"))
                .andExpect(model().attributeExists("paginationBarNumbers"))
                .andExpect(model().attributeExists("intOut"))
                .andDo(MockMvcResultHandlers.print());
        then(groupTabService).should().searchGroupTabs(eq(null), any(Pageable.class));
        then(paginationService).should().getPaginationBarNumbers(anyInt(), anyInt());
        then(categoryService).should().getIntOut();
    }

    @WithMockUser
    @DisplayName("[view][GET] 검색 페이지")
    @Test
    void givenSearchValue_whenRequesting_thenReturnsNewSearchGroupTabPage() throws Exception {
        // Given
        String searchValue = "운동/스포츠";
        given(groupTabService.searchGroupTabs(eq(searchValue), any(Pageable.class))).willReturn(Page.empty());
        given(paginationService.getPaginationBarNumbers(anyInt(), anyInt())).willReturn(List.of(0, 1, 2, 3, 4));
        given(categoryService.getIntOut()).willReturn(List.of(""));
        // When & Then
        mvc.perform(get("/search")
                        .queryParam("searchValue", searchValue)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("groupTabs/search-grouptab"))
                .andExpect(model().attributeExists("groupTabs"))
                .andExpect(model().attributeExists("paginationBarNumbers"))
                .andExpect(model().attributeExists("intOut"))
                .andDo(MockMvcResultHandlers.print());
        then(groupTabService).should().searchGroupTabs(eq(searchValue), any(Pageable.class));
        then(paginationService).should().getPaginationBarNumbers(anyInt(), anyInt());
        then(categoryService).should().getIntOut();
    }

    @WithMockUser
    @DisplayName("[view][GET] 키워드 없이 검색 페이지")
    @Test
    void givenNull_whenRequesting_thenReturnsNewSearchGroupTabPage() throws Exception {
        // Given
        given(groupTabService.searchGroupTabsKeyword(eq(null), eq(null), eq(null), any(Pageable.class))).willReturn(Page.empty());
        given(paginationService.getPaginationBarNumbers(anyInt(), anyInt())).willReturn(List.of(0, 1, 2, 3, 4));
        given(categoryService.getIntOut()).willReturn(List.of());

        // When & Then
        mvc.perform(get("/search-keyword"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("groupTabs/search-grouptab"))
                .andExpect(model().attributeExists("groupTabs"))
                .andExpect(model().attributeExists("paginationBarNumbers"))
                .andExpect(model().attributeExists("intOut"))
                .andDo(MockMvcResultHandlers.print());
        then(groupTabService).should().searchGroupTabsKeyword(eq(null), eq(null), eq(null), any(Pageable.class));
        then(paginationService).should().getPaginationBarNumbers(anyInt(), anyInt());
        then(categoryService).should().getIntOut();
    }

    @WithMockUser
    @DisplayName("[view][GET] 키워드 검색 페이지")
    @Test
    void givenKeyword_whenRequesting_thenReturnsNewSearchGroupTabPage() throws Exception {
        // Given
        String groupName = "운동/스포츠";
        String intOut = "운동/스포츠";
        String groupLocation = "서울";
        given(groupTabService.searchGroupTabsKeyword(eq(groupName), eq(intOut), eq(groupLocation), any(Pageable.class))).willReturn(Page.empty());
        given(paginationService.getPaginationBarNumbers(anyInt(), anyInt())).willReturn(List.of(0, 1, 2, 3, 4));
        given(categoryService.getIntOut()).willReturn(List.of());
        // When & Then
        mvc.perform(get("/search-keyword")
                        .queryParam("groupName", groupName)
                        .queryParam("intOut", intOut)
                        .queryParam("groupLocation", groupLocation)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("groupTabs/search-grouptab"))
                .andExpect(model().attributeExists("groupTabs"))
                .andExpect(model().attributeExists("paginationBarNumbers"))
                .andExpect(model().attributeExists("intOut"))
                .andDo(MockMvcResultHandlers.print());
        then(groupTabService).should().searchGroupTabsKeyword(eq(groupName), eq(intOut), eq(groupLocation), any(Pageable.class));
        then(paginationService).should().getPaginationBarNumbers(anyInt(), anyInt());
        then(categoryService).should().getIntOut();
    }
}