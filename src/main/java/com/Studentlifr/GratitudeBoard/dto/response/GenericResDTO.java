package com.Studentlifr.GratitudeBoard.dto.response;

import org.springframework.http.HttpStatus;

import java.util.List;

public record GenericResDTO<D>(
        D data,
        BasicResDTO result
) {

}
