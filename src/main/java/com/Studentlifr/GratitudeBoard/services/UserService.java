package com.Studentlifr.GratitudeBoard.services;

import com.Studentlifr.GratitudeBoard.dao.UserDAO;
import com.Studentlifr.GratitudeBoard.dto.requests.UserSignupReqDTO;
import com.Studentlifr.GratitudeBoard.dto.response.GenericResDTO;
import com.Studentlifr.GratitudeBoard.dto.response.ResponseDTO;
import com.Studentlifr.GratitudeBoard.services.impl.UserServiceImpl;

import java.util.UUID;

public interface UserService {
    ResponseDTO signup(UserSignupReqDTO userSignupReqDTO);

    UserDAO getUserByEmailId(String emailId);
    ResponseDTO MakeVerified(String mailId);

    UserDAO getUserById(UUID uid);
    public Boolean isUserVerified(String emailId);
}
