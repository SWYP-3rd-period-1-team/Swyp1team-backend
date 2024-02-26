package com.swig.zigzzang.global.security;

import com.swig.zigzzang.global.exception.HttpExceptionCode;
import com.swig.zigzzang.global.exception.custom.security.TokenExpiredException;
import com.swig.zigzzang.global.redis.RedisService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class JWTUtil {
    public static final String BEARER_TYPE = "Bearer";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String REFRESH_HEADER = "RefreshToken";
    public static final String BEARER_PREFIX = "Bearer ";
    @Autowired
    private RedisService redisService;

    private SecretKey secretKey;

    public JWTUtil(@Value("${spring.jwt.secret}")String secret) {


        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public String getUserId(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("userid", String.class);
    }

    public String getPassword(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("password", String.class);
    }

    public Boolean isExpired(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    public String createJwt(String userid, String password, Long expiredMs) {

        return Jwts.builder()
                .claim("userid", userid)
                .claim("password", password)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(secretKey)
                .compact();
    }
    public String createRefreshToken(String userid, String password, Long expiredMs) {


        String refreshToken = Jwts.builder()
                .claim("userid", userid)
                .claim("password", password)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(secretKey)
                .compact();
        // redis에 RT저장
        redisService.setValues(refreshToken,userid, Duration.ofMillis(expiredMs));


        return refreshToken;
    }
    // Request Header에 Access Token 정보를 추출하는 메서드
    public String getAccessToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    // Request Header에 Refresh Token 정보를 추출하는 메서드
    public String getRefreshToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(REFRESH_HEADER);
        if (StringUtils.hasText(bearerToken)) {
            return bearerToken.substring(7);
        }
        return null;
    }
    public boolean validateToken(HttpServletRequest request) {
        try {
            String token = extractHeader(request);
            Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            throw new JwtException(HttpExceptionCode.EXPIRED_TOKEN.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new JwtException(HttpExceptionCode.JWT_NOT_FOUND.getMessage());
        }
          return true;

    }
    public static String extractHeader(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        String token = authorization.split(" ")[1];
        return token;
    }
}
