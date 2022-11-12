package com.example.hackerton.domain.user.service;

import com.example.hackerton.domain.user.User;
import com.example.hackerton.global.config.JwtTokenProvider;
import com.example.hackerton.global.config.LoginTokenResponse;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final JwtTokenProvider jwtTokenProvider;

    /*로그인 ID, PW 검증*/
    public User userConfirmation(String id, String pw) {
        User result = null;
        HikariDataSource dataSource = dataSourceService.getDataSourceByKey(this.loginDB);
        String userCheckQuery = "SELECT login_id, login_name, level_cd, status_cd, type_cd FROM tb_user WHERE login_id=? AND del_yn='N' AND login_pw=md5(?)";
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement pstmt = con.prepareStatement(userCheckQuery);
            pstmt.setString(1, id);
            pstmt.setString(2, pw);
            ResultSet resultSet = pstmt.executeQuery();
            while(resultSet.next()){
                String loginId = resultSet.getString("login_id");
                String loginName = resultSet.getString("name");
                String level = resultSet.getString("level");
                result = User.builder()
                        .loginId(loginId)
                        .name(loginName)
                        .level(level)
                        .build();
            }

            if (resultSet != null) resultSet.close();
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /*검증 성공 시, 토큰 발급*/
    public LoginTokenResponse createLoginToken(User user) {
        String accessToken = jwtTokenProvider.createAccessToken(user);
        String refreshToken = jwtTokenProvider.createRefreshToken(user);
        return new LoginTokenResponse(accessToken,refreshToken);
    }
}
