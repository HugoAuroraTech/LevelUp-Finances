package br.com.levelupfinances.level_up_finances.controller;

import br.com.levelupfinances.level_up_finances.domain.transaction.dto.CategoryRequestDTO;
import br.com.levelupfinances.level_up_finances.domain.transaction.dto.CategoryResponseDTO;
import br.com.levelupfinances.level_up_finances.service.transaction.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CategoryService categoryService;

    @Operation(description = "Cria uma nova categoria")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Retorna a categoria criada")
    )
    @PostMapping("/category")
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody @Valid CategoryRequestDTO requestDTO){
        CategoryResponseDTO responseDTO = this.categoryService.createCategory(requestDTO);
        return ResponseEntity.ok().body(responseDTO);
    }

}
