package com.Studentlifr.GratitudeBoard.dto.response;

import org.springframework.http.HttpStatus;

public record BasicResDTO(
        String message,
        HttpStatus status
) {
}
