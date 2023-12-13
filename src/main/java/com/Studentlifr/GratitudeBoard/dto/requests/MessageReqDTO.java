package com.Studentlifr.GratitudeBoard.dto.requests;

import com.Studentlifr.GratitudeBoard.dao.UserDAO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.UUID;

@Data
@NoArgsConstructor
public class MessageReqDTO {

    @NotEmpty
    @Size(max = 5000)
    private String message;
    @NonNull
    private Boolean is_Anonymous;
}
