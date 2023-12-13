package com.Studentlifr.GratitudeBoard.controller;

import com.Studentlifr.GratitudeBoard.dto.requests.UserSigninReqDTO;
import com.Studentlifr.GratitudeBoard.dto.requests.UserSignupReqDTO;
import com.Studentlifr.GratitudeBoard.dto.response.BasicResDTO;
import com.Studentlifr.GratitudeBoard.dto.response.GenericResDTO;
import com.Studentlifr.GratitudeBoard.dto.response.ResponseDTO;
import com.Studentlifr.GratitudeBoard.dto.response.TokenResDTO;
import com.Studentlifr.GratitudeBoard.services.UserService;
import com.Studentlifr.GratitudeBoard.services.impl.OtpServiceImpl;
import com.Studentlifr.GratitudeBoard.services.impl.TokenServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final OtpServiceImpl otpService;
    private final TokenServiceImpl tokenService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserService userService, OtpServiceImpl otpService, TokenServiceImpl tokenService)
    {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.otpService = otpService;
        this.tokenService = tokenService;
    }

    @PostMapping("signup")
    ResponseDTO signup(@RequestBody @Valid UserSignupReqDTO userSignupReqDTO) {

        return userService.signup(userSignupReqDTO);
    }
    @PostMapping("login")
    GenericResDTO<TokenResDTO> login(@RequestBody  UserSigninReqDTO userSigninReqDTO) {
        System.out.println(userSigninReqDTO);
        var credentials = new UsernamePasswordAuthenticationToken(userSigninReqDTO.getEmailId(),
                userSigninReqDTO.getPassword());
        var authentication = authenticationManager.authenticate(credentials);
        var payload = tokenService.generateToken(authentication);
        return new GenericResDTO<TokenResDTO>(payload, new BasicResDTO("Login Successful", HttpStatus.OK));
    }
    @GetMapping("renew-token")
    public GenericResDTO<TokenResDTO> refresh(Authentication authentication) {
        var payload = tokenService.generateToken(authentication);
        return new GenericResDTO<TokenResDTO>(payload, new BasicResDTO("Token Renewed", HttpStatus.OK));
    }
    @PostMapping("verifyEmail")
    ResponseDTO verifyEmail(@RequestParam UUID id) {
        return otpService.verifyEmail(id);
    }
    @PostMapping("verifyOtp")
    ResponseDTO verifyOtp(@RequestParam String mailId, @RequestParam String otp) {
        if (otpService.verifyOtp(mailId, otp))
            return userService.MakeVerified(mailId);
        return new ResponseDTO( new BasicResDTO("OTP is wrong", HttpStatus.NOT_FOUND));
    }

}
