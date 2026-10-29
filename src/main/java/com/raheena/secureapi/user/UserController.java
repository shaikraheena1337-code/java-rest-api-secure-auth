package com.raheena.secureapi.user;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository users;
    public UserController(UserRepository users) { this.users = users; }

    @GetMapping("/me")
    public UserResponse currentUser(Authentication authentication) {
        AppUser user = users.findByEmailIgnoreCase(authentication.getName())
            .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                org.springframework.http.HttpStatus.NOT_FOUND, "User not found."));
        return UserResponse.from(user);
    }
}
