package br.com.levelupfinances.level_up_finances.repository;

import br.com.levelupfinances.level_up_finances.domain.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRespository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findByUserId(Long id);
}
