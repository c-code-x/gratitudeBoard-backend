package com.Studentlifr.GratitudeBoard.dto.response;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
public class PublicMessagesResDTO {
    @NotEmpty
    private String message;
    @NotEmpty
    private String name = "Anonymous";
}
