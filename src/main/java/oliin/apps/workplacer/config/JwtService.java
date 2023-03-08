package oliin.apps.workplacer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import oliin.apps.workplacer.auth.domain.model.UserModel;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    // Placeholder, BE Keystore, move value to yml file
    private static final String SECRET_KEY = "432646294A404E635266556A586E3272357538782F4125442A472D4B61506453";
    private static final int TOKEN_DURATION = 24;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    // TODO Implement it
//    public UserModel extractUserModel(String token) {
//        LinkedHashMap json = extractClaim(token, (Claims claims) -> claims.get("user_model", LinkedHashMap.class));
//        ObjectMapper objectMapper = new ObjectMapper();

//        UserModel premierDriverInfoDTO = objectMapper.convertValue(json, UserModel.class);

//        Map<String, String> map = objectMapper.convertValue(json, Map.class);
//        return premierDriverInfoDTO;
//    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserModel userModel) {
        return generateToken(new HashMap<>(), userModel);
    }

    public String generateToken(Map<String, Object> extraClaims, UserModel userModel) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userModel.getUsername())
                .claim("user_model", userModel)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * TOKEN_DURATION))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
