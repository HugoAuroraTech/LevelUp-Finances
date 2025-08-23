package br.com.levelupfinances.level_up_finances.domain.transaction.dto;

import br.com.levelupfinances.level_up_finances.domain.transaction.Category;

public record CategoryResponseDTO(
        Long id,
        String title,
        String color
) {
    public static CategoryResponseDTO fromEntity(Category category){
        return new CategoryResponseDTO(
                category.getId(),
                category.getTitle(),
                category.getColor()
        );
    }
}
