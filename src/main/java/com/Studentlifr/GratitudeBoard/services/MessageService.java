package com.Studentlifr.GratitudeBoard.services;

import com.Studentlifr.GratitudeBoard.dao.MessageDAO;
import com.Studentlifr.GratitudeBoard.dto.requests.MessageReqDTO;
import com.Studentlifr.GratitudeBoard.dto.response.GenericResDTO;
import com.Studentlifr.GratitudeBoard.dto.response.PublicMessagesResDTO;
import com.Studentlifr.GratitudeBoard.dto.response.ResponseDTO;

import java.util.List;

public interface MessageService {
    public ResponseDTO addMessage(MessageReqDTO messageDTO, String userId);
    public GenericResDTO<List<PublicMessagesResDTO>> getAllMessages();

}
