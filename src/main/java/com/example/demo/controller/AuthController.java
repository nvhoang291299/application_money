package com.example.demo.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Account;
import com.example.demo.entity.RefreshToken;
import com.example.demo.entity.Role;
import com.example.demo.exception.TokenRefreshException;
import com.example.demo.repository.IAccountRepository;
import com.example.demo.repository.IRoleRepository;
import com.example.demo.request.AuthRequest;
import com.example.demo.request.RefreshTokenRequest;
import com.example.demo.request.SignupRequest;
import com.example.demo.response.JwtResponse;
import com.example.demo.response.MessageResponse;
import com.example.demo.response.TokenRefreshResponse;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.service.IAccountService;
import com.example.demo.service.IRefreshTokenService;
import com.example.demo.service.impl.MyUserDetails;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IRefreshTokenService refreshTokenService;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtToken(myUserDetails);

        List<String> roles = myUserDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(myUserDetails.getId());

        return ResponseEntity
                .ok(new JwtResponse(jwt, myUserDetails.getUsername(), refreshToken.getRefreshToken(), roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
        if (accountService.checkUsername(signupRequest)) {
            return ResponseEntity.badRequest().body(new MessageResponse("username đã tồn tại!!!"));
        }

        if (accountService.checkPhoneNumber(signupRequest)) {
            return ResponseEntity.badRequest().body(new MessageResponse("số điện thoại của bạn đã bị trùng!"));
        }

        Account account = new Account(signupRequest.getUsername(), signupRequest.getPhoneNumber(),
                encoder.encode(signupRequest.getPassword()));

        String role = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (role == null) {
            Role userRole = roleRepository.findByNameRole("ROLE_USER");
            roles.add(userRole);
        }
        account.setRoles(roles);
        accountService.save(account);

        return ResponseEntity.ok(new MessageResponse("Đăng kí thành công!"));
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody RefreshTokenRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByRefreshToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getAccount).map(account -> {
                    String token = jwtUtils.generateTokenFromUsername();
                    return new TokenRefreshResponse(token, requestRefreshToken);
                })
                .orElseThrow(
                        () -> new TokenRefreshException(requestRefreshToken, "Refresh token không có trong database!"));
    }
}
