package com.example.hackerton.global.config;

import com.example.hackerton.domain.user.Store;
import com.example.hackerton.domain.user.User;
import com.example.hackerton.domain.user.UserPermission;
import com.example.hackerton.domain.user.dto.LoginDto;
import com.example.hackerton.global.common.CodeSet;
import com.example.hackerton.global.common.EndPoint;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    Gson gson = new GsonBuilder().disableHtmlEscaping().create();;

    private final JwtTokenProvider jwtTokenProvider;

    @Value("${jwt.prefix}")
    private String jwtPrefix;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.debug("URL : {}",request.getRequestURL());
        if (request.getServletPath().equals(EndPoint.AUTH_USER_LOGIN) || request.getServletPath().equals(EndPoint.AUTH_REFRESH)
                || request.getServletPath().equals(EndPoint.AUTH_USER_SIGNUP) || request.getServletPath().equals(EndPoint.AUTH_STORE_SIGNUP) ||
                request.getServletPath().equals(EndPoint.AUTH_STORE_LOGIN) ||
                request.getServletPath().equals(EndPoint.AUTH_REFRESH) ||
                request.getServletPath().equals("/doc") ||
                request.getServletPath().equals("/favicon.ico") ||
                request.getServletPath().contains("swagger-ui") ||
                request.getServletPath().contains("swagger-resources") ||
                request.getServletPath().contains("api-docs")
        ) {
            log.debug("JwtTokenAuthenticationFilter.SKIP!");
        } else {
            log.debug("JwtTokenAuthenticationFilter.CHECK!");

            String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
            if (token != null && jwtTokenProvider.validateToken(JwtTokenType.ACCESS, token)) {   // token ??????
                LoginDto dto = jwtTokenProvider.getInfo(token);
                if (dto.getStore() == null) {
                    User user = dto.getUser();
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                            user.getLoginId(),
                            user,
                            new ArrayList<>(Collections.singleton(new SimpleGrantedAuthority(UserPermission.findByCode(user.getLevel()).toString())))
                    );
                    SecurityContextHolder.getContext().setAuthentication(auth);

                    log.info("JwtTokenAuthenticationFilter.Valid User : {}", user);
                } else {
                    Store store = dto.getStore();
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                            store.getLoginId(),
                            store,
                            new ArrayList<>(Collections.singleton(new SimpleGrantedAuthority(UserPermission.findByCode(store.getLevel()).toString())))
                    );
                    SecurityContextHolder.getContext().setAuthentication(auth);

                    log.info("JwtTokenAuthenticationFilter.Valid User : {}", store);
                }
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.addHeader("Content-Type", "application/json; charset=UTF-8");

                String responseJson = gson.toJson(new RejectResponse(CodeSet.AUTH_ACCESS_TOKEN_EXPIRE));

                response.getWriter().write(responseJson);
                response.getWriter().close();
                log.error("URL : {}, code : {}, msg : {}", request.getRequestURL(), CodeSet.AUTH_ACCESS_TOKEN_EXPIRE, CodeSet.AUTH_ACCESS_TOKEN_EXPIRE.getMessage());
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

}
