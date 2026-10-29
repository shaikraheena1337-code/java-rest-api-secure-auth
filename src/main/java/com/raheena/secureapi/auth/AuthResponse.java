package com.raheena.secureapi.auth;

public record AuthResponse(String tokenType, String accessToken, long expiresInSeconds) {}
