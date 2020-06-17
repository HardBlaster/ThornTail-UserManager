package hu.innovitech.web.token;

import io.jsonwebtoken.*;

import javax.enterprise.context.ApplicationScoped;
import java.util.Date;

@ApplicationScoped
public class TokenHandler implements TokenProvider {

    private static final String SK = "This may NOT be that secure";

    public static final long EXPIRE_INTERVAL = 3600000;

    @Override
    public String generateToken(String username, String role) {
        Date now = new Date();
        Date expires = new Date(now.getTime() + EXPIRE_INTERVAL);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expires)
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS256, SK)
                .compact();
    }

    @Override
    public String validateToken(String token) {
        try {

            return Jwts.parser()
                    .setSigningKey(SK)
                    .parseClaimsJws(token)
                    .getBody()
                    .get("role", String.class);

        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public String getUsername(String token) {
        try {

            return Jwts.parser()
                    .setSigningKey(SK)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            e.printStackTrace();

            return null;
        }
    }
}
