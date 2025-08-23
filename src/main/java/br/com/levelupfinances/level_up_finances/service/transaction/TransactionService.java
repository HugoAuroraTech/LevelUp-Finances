package br.com.levelupfinances.level_up_finances.service.transaction;

import br.com.levelupfinances.level_up_finances.domain.transaction.Category;
import br.com.levelupfinances.level_up_finances.domain.transaction.Transaction;
import br.com.levelupfinances.level_up_finances.domain.transaction.dto.TransactionRequestDTO;
import br.com.levelupfinances.level_up_finances.domain.transaction.dto.TransactionResponseDTO;
import br.com.levelupfinances.level_up_finances.domain.transaction.dto.TransactionSummaryDTO;
import br.com.levelupfinances.level_up_finances.domain.transaction.enums.TransactionType;
import br.com.levelupfinances.level_up_finances.domain.user.User;
import br.com.levelupfinances.level_up_finances.exception.CategoryNotFoundException;
import br.com.levelupfinances.level_up_finances.exception.TransactionNotFoundException;
import br.com.levelupfinances.level_up_finances.exception.UserNotFoundException;
import br.com.levelupfinances.level_up_finances.repository.CategoryRepository;
import br.com.levelupfinances.level_up_finances.repository.TransactionReposity;
import br.com.levelupfinances.level_up_finances.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionReposity transactionReposity;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    public TransactionResponseDTO createTransaction(TransactionRequestDTO requestDTO){
        Category category = categoryRepository.findById(requestDTO.categoryId()).orElseThrow(() -> new CategoryNotFoundException("Categoria não encontrada"));
        User userId = userRepository.findById(requestDTO.userId()).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
        Transaction transaction = new Transaction();
        transaction.setTitle(requestDTO.title());
        transaction.setDescription(requestDTO.description());
        transaction.setValue(requestDTO.value());
        transaction.setUser(userId);
        transaction.setCategory(category);
        Transaction transactionSave = transactionReposity.save(transaction);
        return TransactionResponseDTO.fromEntity(transactionSave);
    }

    public List<TransactionResponseDTO> searchAllTransactionsUserId(Long id){
         return transactionReposity
                .findByUserId(id)
                .stream()
                .map(TransactionResponseDTO::fromEntity)
                .toList();
    }


    public TransactionSummaryDTO getSummary(Long id){
        List<Transaction> transactions = transactionReposity.findByUserId(id);

        BigDecimal totalIncome = transactions.stream()
                .filter(t -> t.getTransactionType() == TransactionType.INCOME)
                .map(Transaction::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalExpense = transactions.stream()
                .filter(t -> t.getTransactionType() == TransactionType.EXPENSE)
                .map(Transaction::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal balance = totalIncome.subtract(totalExpense);

        return new TransactionSummaryDTO(totalIncome, totalExpense, balance);
    }

}
