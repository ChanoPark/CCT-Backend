package com.example.hackerton.global.config;

import com.example.hackerton.domain.user.User;
import com.example.hackerton.domain.user.UserPermission;
import com.example.hackerton.global.util.DateUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtTokenProvider {

    @Value("${jwt.prefix}")
    private String jwtPrefix;

    // 토큰 유효시간 10분
    private static final long ACCESS_TOKEN_VALID_TIME = DateUtil.toMinuteMillis(1400);
    // 토큰 유효시간 24시간
    private static final long REFRESH_TOKEN_VALID_TIME = DateUtil.toHourMillis(24);

    private static final String JWT_SECRET = "J@NcQfTjWnZr4u7x!A%D*G-Kp2s5v8y/B?E(H+KbPeShVmYq3t6w9z$C&F)aPdSg";

    private static final byte[] secretKey = JWT_SECRET.getBytes();

    public String createAccessToken(User userInfo) {
        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(secretKey), SignatureAlgorithm.HS512)
                .setSubject(userInfo.getLoginId())
                .setId(UUID.randomUUID().toString())
                .claim("type", JwtTokenType.ACCESS)
                .claim("login_name", userInfo.getName())
                .claim("level_cd", userInfo.getRole())
                .claim("role", UserPermission.findByCode(userInfo.getLevel()).toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALID_TIME))
                .signWith(SignatureAlgorithm.HS256, secretKey)  // 사용할 암호화 알고리즘과
                .compact();
    }

    public String createRefreshToken(User userInfo) {
        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(secretKey), SignatureAlgorithm.HS512)
                .setSubject(userInfo.getLoginId())
                .setId(UUID.randomUUID().toString())
                .claim("type", JwtTokenType.REFRESH)
                .claim("login_name", userInfo.getName())
                .claim("level_cd", userInfo.getLevel())
                .claim("role", UserPermission.findByCode(userInfo.getLevel()).toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_VALID_TIME))
                .signWith(SignatureAlgorithm.HS256, secretKey)  // 사용할 암호화 알고리즘과
                .compact();
    }

    // 토큰에서 회원ID 정보 추출
    public User getUserInfo(String jwtToken) {
        if (jwtToken.startsWith(jwtPrefix)) {
            jwtToken = jwtToken.substring(jwtPrefix.length());
        }
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken).getBody();
        String loginId = claims.getSubject();
        String name = claims.get("login_name", String.class);
        String level = claims.get("level_cd", String.class);

        return User.builder()
                .loginId(loginId)
                .name(name)
                .level(level)
                .role(UserPermission.findByCode(level))
                .build();
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader(HttpHeaders.AUTHORIZATION);
    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(JwtTokenType tokenType, String jwtToken) {
        if (jwtToken.startsWith(jwtPrefix)) {
            jwtToken = jwtToken.substring(jwtPrefix.length());
        }
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            if (tokenType.equals(claims.getBody().get("type", String.class))) {

            }
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
