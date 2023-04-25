package com.assaf.StoreApi.auth;

import com.assaf.StoreApi.config.JwtService;
import com.assaf.StoreApi.enums.Role;
import com.assaf.StoreApi.hendlers.custom.EmailAlreadyExistsException;
import com.assaf.StoreApi.hendlers.custom.EmailNotFoundException;
import com.assaf.StoreApi.user.User;
import com.assaf.StoreApi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        User user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        boolean isEmailExists = userRepository.existsByEmail(request.getEmail());
        if(isEmailExists){
            throw new EmailAlreadyExistsException("Email already exists|البريد الالكتروني موجود من قبل");
        }
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new EmailNotFoundException("Email Not Found|البريد الالكتروني غير مسجل"));
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
