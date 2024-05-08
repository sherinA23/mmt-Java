package com.poc.makemytrip.service;

import com.poc.makemytrip.config.AuthenticationRequest;
import com.poc.makemytrip.config.AuthenticationResponse;
import com.poc.makemytrip.config.JwtService;
import com.poc.makemytrip.config.RegisterRequest;
import com.poc.makemytrip.dao.TokenDao;
import com.poc.makemytrip.entity.Token;
import com.poc.makemytrip.entity.TokenType;
import com.poc.makemytrip.entity.User;
import com.poc.makemytrip.dao.UsersDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    @Autowired
    private UsersDao user_dao;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final TokenDao tokenRepository;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .name(request.getName())
                .emailID(request.getEmailID())
                .password(passwordEncoder.encode((request.getPassword())))
                .role(request.getRole())
                .build();
        if (user_dao.findByEmailID(user.getEmailID()).isPresent()){
            System.out.println("Email ID already exists");
        }else{
            System.out.println("Registration Complete");
            user_dao.save(user);
        }
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmailID(),
                        request.getPassword()
                )
        );

        var user = user_dao.findByEmailID(request.getEmailID())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getID());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }


}
