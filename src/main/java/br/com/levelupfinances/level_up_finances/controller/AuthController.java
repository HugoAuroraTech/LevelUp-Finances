package br.com.levelupfinances.level_up_finances.controller;

import br.com.levelupfinances.level_up_finances.domain.user.User;
import br.com.levelupfinances.level_up_finances.domain.user.auth.dto.LoginRequestDTO;
import br.com.levelupfinances.level_up_finances.domain.user.auth.dto.RegisterRequestDTO;
import br.com.levelupfinances.level_up_finances.domain.user.auth.dto.RegisterResponseDTO;
import br.com.levelupfinances.level_up_finances.domain.user.auth.dto.TokenDTO;
import br.com.levelupfinances.level_up_finances.exception.UserAlreadyExistsException;
import br.com.levelupfinances.level_up_finances.infra.config.TokenService;
import br.com.levelupfinances.level_up_finances.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Operation(description = "Faz o login e gera o token de acesso para o usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o token de acesso JWT"),
            @ApiResponse(responseCode = "401", description = "Credênciais inválidas")
    })
    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid LoginRequestDTO requestDTO){
        var emailPassword = new UsernamePasswordAuthenticationToken(requestDTO.email(), requestDTO.password());
        var auth = this.authenticationManager.authenticate(emailPassword);

        var token = this.tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new TokenDTO(token));
    }

    @Operation(description = "Registra um novo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o usuário cadastrado"),
            @ApiResponse(responseCode = "400", description = "Usuário já cadastrado")
    })
    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody @Valid RegisterRequestDTO requestDTO){
        if(userRepository.findByEmail(requestDTO.email()) != null) throw new UserAlreadyExistsException("Usuário já cadastrado");

        String passwordHash = new BCryptPasswordEncoder().encode(requestDTO.password());

        User user = new User(requestDTO.name(), requestDTO.email(), passwordHash, requestDTO.role());

        this.userRepository.save(user);

        RegisterResponseDTO responseDTO = RegisterResponseDTO.fromEntity(user);

        return ResponseEntity.ok().body(responseDTO);
    }
}

