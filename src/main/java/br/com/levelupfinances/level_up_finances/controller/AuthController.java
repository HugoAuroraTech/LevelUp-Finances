package br.com.levelupfinances.level_up_finances.controller;

import br.com.levelupfinances.level_up_finances.domain.user.User;
import br.com.levelupfinances.level_up_finances.domain.user.auth.dto.LoginRequestDTO;
import br.com.levelupfinances.level_up_finances.domain.user.auth.dto.RegisterRequestDTO;
import br.com.levelupfinances.level_up_finances.domain.user.auth.dto.RegisterResponseDTO;
import br.com.levelupfinances.level_up_finances.domain.user.auth.dto.TokenDTO;
import br.com.levelupfinances.level_up_finances.infra.config.TokenService;
import br.com.levelupfinances.level_up_finances.repository.UserRepository;
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

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginRequestDTO requestDTO){
        var emailPassword = new UsernamePasswordAuthenticationToken(requestDTO.email(), requestDTO.password());
        var auth = this.authenticationManager.authenticate(emailPassword);

        var token = this.tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new TokenDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody @Valid RegisterRequestDTO requestDTO){
        if(userRepository.findByEmail(requestDTO.email()) != null) return ResponseEntity.badRequest().build();

        String passwordHash = new BCryptPasswordEncoder().encode(requestDTO.password());

        User user = new User(requestDTO.name(), requestDTO.email(), passwordHash, requestDTO.role());

        this.userRepository.save(user);

        RegisterResponseDTO responseDTO = RegisterResponseDTO.fromEntity(user);

        return ResponseEntity.ok().body(responseDTO);
    }
}

