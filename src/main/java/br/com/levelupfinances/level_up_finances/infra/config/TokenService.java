package br.com.levelupfinances.level_up_finances.infra.config;

import br.com.levelupfinances.level_up_finances.domain.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public  String generateToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("levelup-finances")
                    .withSubject(user.getEmail())
                    .withExpiresAt(gerarTempoDeExpirar())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error", exception);
        }
    }

    public String valideToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("levelup-finances")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTCreationException exception){
            return "";
        }
    }

    private Instant gerarTempoDeExpirar(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}