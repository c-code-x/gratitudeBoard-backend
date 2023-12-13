package com.Studentlifr.GratitudeBoard.dto.response;

import com.Studentlifr.GratitudeBoard.dao.UserDAO;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AdminMessagesResDTO {
    @NotEmpty
    private UserDAO user;
    @NotEmpty
    private String message;
    @NotEmpty
    private Boolean is_Anonymous;
    @NotEmpty
    private String date;
}
