package org.example.car_test.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.example.car_test.model.User;
import org.example.car_test.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtility {
    private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    private static final long ACCESS_TOKEN_VALIDITY = 15 *60 * 1000; // 15 minutes
//    private static final long REFRESH_TOKEN_VALIDITY = 15 *60 * 1000; // 7 days
    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    private final UserRepository userRepository;

    public JwtUtility(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public String generateToken(String username, boolean isAccessToken) {
        User user = userRepository.findByUsername(username);
        return Jwts.builder()
                .setSubject(username)
                .claim("role", user.getRole().name()) // Добавляем роль
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }



    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            System.out.println("Token validation failed: " + ex.getMessage());
            return false;
        }
    }
    public String extractRole(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);
    }


    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}
