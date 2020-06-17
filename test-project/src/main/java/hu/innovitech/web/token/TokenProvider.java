package hu.innovitech.web.token;

public interface TokenProvider {

    String generateToken(String username, String role);

    String validateToken(String token);

    String getUsername(String token);
}
