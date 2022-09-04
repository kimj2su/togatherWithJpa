package team1.togather.service.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team1.togather.repository.member.CategoryRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<String> getIntOut() {
        return categoryRepository.findByIntOut();
    }

    public List<String> getIntIn(String searchValue) {
        return categoryRepository.findByIntIn(searchValue);
    }

    public List<String> getFirstOption(String searchValue) {
        return categoryRepository.findByFirstOption(searchValue);
    }

}
