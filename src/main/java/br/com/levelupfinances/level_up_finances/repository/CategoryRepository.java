package br.com.levelupfinances.level_up_finances.repository;

import br.com.levelupfinances.level_up_finances.domain.transaction.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> existsByTitle(String title);
}
