package com.Studentlifr.GratitudeBoard.services.impl;

import com.Studentlifr.GratitudeBoard.dao.UserDAO;
import com.Studentlifr.GratitudeBoard.dao.repository.UserRepository;
import com.Studentlifr.GratitudeBoard.dto.requests.UserSignupReqDTO;
import com.Studentlifr.GratitudeBoard.dto.response.BasicResDTO;
import com.Studentlifr.GratitudeBoard.dto.response.GenericResDTO;
import com.Studentlifr.GratitudeBoard.dto.response.ResponseDTO;
import com.Studentlifr.GratitudeBoard.exceptions.ApiRuntimeException;
import com.Studentlifr.GratitudeBoard.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }
    public ResponseDTO signup(UserSignupReqDTO userSignupReqDTO) {
        Boolean existingUser = userRepository.existsByEmailId(userSignupReqDTO.getEmailId());
        if (existingUser) {
            throw  new ApiRuntimeException("User already exists", HttpStatus.BAD_REQUEST);
        }
        UserDAO user = UserDAO.builder()
                .name(userSignupReqDTO.getName())
                .emailId(userSignupReqDTO.getEmailId())
                .password(new BCryptPasswordEncoder().encode(userSignupReqDTO.getPassword()))
                .build();
        userRepository.save(user);
        return new ResponseDTO( new BasicResDTO("User signed up successfully", HttpStatus.CREATED));
    }

    public UserDAO getUserByEmailId(String emailId) {
        Optional<UserDAO> optionalUserEntity = userRepository.findByEmailId(emailId);
        if (optionalUserEntity.isEmpty())
            throw new ApiRuntimeException("User with email:\"" + emailId + "\" does not exist", HttpStatus.NOT_FOUND);
        return optionalUserEntity.get();
    }
    public ResponseDTO MakeVerified(String mailId){
        UserDAO userDAO = getUserByEmailId(mailId);
        userDAO.setIsVerified(true);
        userRepository.save(userDAO);
        return new ResponseDTO( new BasicResDTO("User verified successfully", HttpStatus.OK));
    }

    @Override
    public UserDAO getUserById(UUID uid) {
        Optional<UserDAO> optionalUserEntity = userRepository.findById(uid);
        if (optionalUserEntity.isEmpty())
            throw new ApiRuntimeException("User with id:\"" + uid + "\" does not exist", HttpStatus.NOT_FOUND);
        return optionalUserEntity.get();
    }
    public Boolean isUserVerified(String emailId){
        UserDAO userDAO = getUserByEmailId(emailId);
        return userDAO.getIsVerified();
    }
}
