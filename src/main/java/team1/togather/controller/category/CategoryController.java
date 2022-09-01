package team1.togather.controller.category;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import team1.togather.service.member.CategoryService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService cateGoryService;

    @ResponseBody
    @GetMapping("/category")
    public List<String> category(String searchValue, long sequence) {
        System.out.println("searchValue = " + searchValue);
        List<String> categories= new ArrayList<>();
        if(sequence==2) {
            categories = cateGoryService.getIntIn(searchValue);
        }else if(sequence==3) {
            categories = cateGoryService.getFirstOption(searchValue);
        }
        System.out.println("categories = " + categories);
        return categories;
    }
}
