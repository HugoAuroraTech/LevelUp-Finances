package br.com.levelupfinances.level_up_finances.domain.user.auth.dto;

import br.com.levelupfinances.level_up_finances.domain.user.User;
import br.com.levelupfinances.level_up_finances.domain.user.enums.UserRole;

import java.time.LocalDateTime;

public record RegisterResponseDTO(
        Long id,
        String name,
        String email,
        UserRole role,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static RegisterResponseDTO fromEntity(User user){
        return new RegisterResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
