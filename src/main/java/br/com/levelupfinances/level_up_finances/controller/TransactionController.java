package br.com.levelupfinances.level_up_finances.controller;

import br.com.levelupfinances.level_up_finances.domain.transaction.dto.TransactionRequestDTO;
import br.com.levelupfinances.level_up_finances.domain.transaction.dto.TransactionResponseDTO;
import br.com.levelupfinances.level_up_finances.service.transaction.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
