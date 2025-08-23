package br.com.levelupfinances.level_up_finances.repository;

import br.com.levelupfinances.level_up_finances.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionReposity extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUserId(Long id);
}
