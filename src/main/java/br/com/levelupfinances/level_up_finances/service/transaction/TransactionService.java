package br.com.levelupfinances.level_up_finances.service.transaction;

import br.com.levelupfinances.level_up_finances.domain.transaction.Category;
import br.com.levelupfinances.level_up_finances.domain.transaction.Transaction;
import br.com.levelupfinances.level_up_finances.domain.transaction.dto.TransactionRequestDTO;
import br.com.levelupfinances.level_up_finances.domain.transaction.dto.TransactionResponseDTO;
import br.com.levelupfinances.level_up_finances.exception.CategoryNotFoundException;
import br.com.levelupfinances.level_up_finances.repository.CategoryRepository;
import br.com.levelupfinances.level_up_finances.repository.TransactionReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionReposity transactionReposity;

    @Autowired
    private CategoryRepository categoryRepository;

    public TransactionResponseDTO createTransaction(TransactionRequestDTO requestDTO){
        Category category = categoryRepository.findById(requestDTO.categoryId()).orElseThrow(() -> new CategoryNotFoundException("Categoria não encontrada"));
        Transaction transaction = new Transaction();
        transaction.setTitle(requestDTO.title());
        transaction.setDescription(requestDTO.description());
        transaction.setValue(requestDTO.value());
        transaction.setCategory(category);
        Transaction transactionSave = transactionReposity.save(transaction);
        return TransactionResponseDTO.fromEntity(transactionSave);
    }

}
