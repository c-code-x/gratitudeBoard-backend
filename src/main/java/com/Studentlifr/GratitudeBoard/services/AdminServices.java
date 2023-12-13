package com.Studentlifr.GratitudeBoard.services;

import com.Studentlifr.GratitudeBoard.dao.UserDAO;
import com.Studentlifr.GratitudeBoard.dto.response.AdminMessagesResDTO;
import com.Studentlifr.GratitudeBoard.dto.response.GenericResDTO;
import com.Studentlifr.GratitudeBoard.dto.response.ResponseDTO;

import java.util.List;
import java.util.UUID;

public interface AdminServices {
    public GenericResDTO<List<AdminMessagesResDTO>> getAllMessages();
    public GenericResDTO<List<UserDAO>> getAllUsers();
    public ResponseDTO deleteUserById(UUID id);
    public ResponseDTO deleteMessageById(UUID id);
    public ResponseDTO promoteUserById(UUID id);
    public ResponseDTO demoteUserById(UUID id);

}
