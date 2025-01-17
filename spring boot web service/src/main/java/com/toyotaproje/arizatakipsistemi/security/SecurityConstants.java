package com.toyotaproje.arizatakipsistemi.security;

//Header’i oluşturmak için gerekli constanlar ve sign-up path’ini içeren bir constants sınıfı oluşturalım.
public class SecurityConstants {
    public static final String SIGN_UP_URL = "/personel/sign-up";
    public static final String SECRET = "Toyota";
    public static final long EXPIRATION_TIME = 432_000_000; // 5 gün
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}