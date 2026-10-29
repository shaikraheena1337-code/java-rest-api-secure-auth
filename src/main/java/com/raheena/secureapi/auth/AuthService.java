package com.raheena.secureapi.auth;
import org.springframework.security.core.AuthenticationException;
import com.raheena.secureapi.security.JwtService;
import com.raheena.secureapi.user.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {
    private final UserRepository users;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtService jwtService;

    public AuthService(UserRepository users, PasswordEncoder encoder,
        AuthenticationManager authenticationManager, CustomUserDetailsService userDetailsService,
        JwtService jwtService) {
        this.users = users; this.encoder = encoder; this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService; this.jwtService = jwtService;
    }

    public UserResponse register(RegisterRequest request) {
        String email = request.email().trim().toLowerCase();
        if (users.existsByEmailIgnoreCase(email)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "An account with this email already exists.");
        }
        try {
            AppUser saved = users.save(new AppUser(request.name().trim(), email, encoder.encode(request.password())));
            return UserResponse.from(saved);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "An account with this email already exists.");
        }
    }

    public AuthResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email().trim().toLowerCase(), request.password()));
        } catch (AuthenticationException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password.");
        }
        UserDetails details = userDetailsService.loadUserByUsername(request.email().trim().toLowerCase());
        return new AuthResponse("Bearer", jwtService.generateToken(details), jwtService.getExpirationSeconds());
    }
}
