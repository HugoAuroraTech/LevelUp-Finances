package br.com.levelupfinances.level_up_finances.controller;

import br.com.levelupfinances.level_up_finances.domain.transaction.dto.TransactionRequestDTO;
import br.com.levelupfinances.level_up_finances.domain.transaction.dto.TransactionResponseDTO;
import br.com.levelupfinances.level_up_finances.domain.transaction.dto.TransactionSummaryDTO;
import br.com.levelupfinances.level_up_finances.service.transaction.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> createTransaction(@RequestBody @Valid TransactionRequestDTO requestDTO){
        TransactionResponseDTO responseDTO = this.transactionService.createTransaction(requestDTO);
        return ResponseEntity.ok().body(responseDTO);
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<TransactionResponseDTO>> findAllTransactions(@PathVariable @Valid Long id){
        List<TransactionResponseDTO> transactionResponseDTOS = transactionService.searchAllTransactionsUserId(id);
        return ResponseEntity.ok().body(transactionResponseDTOS);
    }

    @GetMapping("/{id}/summary")
    public ResponseEntity<TransactionSummaryDTO> getSummary(@PathVariable @Valid Long id) {
        TransactionSummaryDTO summary = transactionService.getSummary(id);
        return ResponseEntity.ok().body(summary);
    }
}
