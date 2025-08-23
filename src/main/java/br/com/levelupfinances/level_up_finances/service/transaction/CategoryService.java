package br.com.levelupfinances.level_up_finances.service.transaction;

import br.com.levelupfinances.level_up_finances.domain.transaction.Category;
import br.com.levelupfinances.level_up_finances.domain.transaction.dto.CategoryRequestDTO;
import br.com.levelupfinances.level_up_finances.domain.transaction.dto.CategoryResponseDTO;
import br.com.levelupfinances.level_up_finances.exception.InvalidCategoryDataException;
import br.com.levelupfinances.level_up_finances.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO){
        if (categoryRepository.existsByTitle(categoryRequestDTO.title().trim()).isPresent()) {
            throw new InvalidCategoryDataException("Já existe uma categoria com este título");
        }

        Category category = new Category();
        category.setTitle(categoryRequestDTO.title().trim());
        category.setColor(categoryRequestDTO.color().toUpperCase());
        Category categorySave = categoryRepository.save(category);
        return CategoryResponseDTO.fromEntity(categorySave);
    }
}
