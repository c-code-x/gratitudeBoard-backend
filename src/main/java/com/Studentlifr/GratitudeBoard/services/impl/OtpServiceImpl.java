package com.Studentlifr.GratitudeBoard.services.impl;

import com.Studentlifr.GratitudeBoard.dao.UserDAO;
import com.Studentlifr.GratitudeBoard.dao.repository.UserRepository;
import com.Studentlifr.GratitudeBoard.dto.response.GenericResDTO;
import com.Studentlifr.GratitudeBoard.dto.response.ResponseDTO;
import com.Studentlifr.GratitudeBoard.exceptions.ApiRuntimeException;
import com.Studentlifr.GratitudeBoard.services.UserService;
import com.Studentlifr.GratitudeBoard.utility.OTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class OtpServiceImpl {
    private final UserRepository userRepository;
    private final Map<String, OTP> otpMap;
    private final EmailService emailService;

    @Autowired
    public OtpServiceImpl( UserRepository userRepository, EmailService emailService){
        this.otpMap = new HashMap<>();
        this.userRepository = userRepository;
        this.emailService = emailService;
    }
    public ResponseDTO verifyEmail(UUID id) {
        Optional<UserDAO> optionalUserDAO = userRepository.findById(id);
        if(optionalUserDAO.isEmpty()){
            throw new ApiRuntimeException("User does not exist", HttpStatus.NOT_FOUND);
        }
        if(optionalUserDAO.get().getIsVerified()){
            throw new ApiRuntimeException("Email already verified", HttpStatus.BAD_REQUEST);
        }
        OTP otp = OTP.generateOTP(optionalUserDAO.get().getEmailId());
        saveOTP(optionalUserDAO.get().getId().toString(), otp);
       return emailService.sendEmail(optionalUserDAO.get().getEmailId(), "GRATITUDE BOARD VERIFY EMAIL", "Your OTP is for Verifying is " + otp.getValue());

    }

    public boolean verifyOtp(String emailId, String otpString) {
        Optional<UserDAO> userDAO = userRepository.findByEmailId(emailId);
        if(userDAO.isEmpty()){
            throw new ApiRuntimeException("User does not exist", HttpStatus.NOT_FOUND);
        }
        OTP storedOTP = getOTP(userDAO.get().getId().toString());
        if (storedOTP.isExpired())
            throw new ApiRuntimeException("OTP expired", HttpStatus.BAD_REQUEST);
        return storedOTP.getValue().equals(otpString);
    }

    private void saveOTP(String userName, OTP otp) {
        otpMap.put(userName, otp);
    }

    private OTP getOTP(String userName) {
        if (!otpMap.containsKey(userName))
            throw new ApiRuntimeException("OTP not found", HttpStatus.BAD_REQUEST);
        return otpMap.get(userName);
    }

}
