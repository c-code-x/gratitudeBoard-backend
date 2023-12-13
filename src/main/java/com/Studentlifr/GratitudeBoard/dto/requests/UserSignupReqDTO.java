package com.Studentlifr.GratitudeBoard.dto.requests;

import com.Studentlifr.GratitudeBoard.enumerators.ROLES;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;
import org.springframework.boot.convert.DataSizeUnit;

@Data
public class UserSignupReqDTO {
    @NotEmpty(message = "Registration ID cannot be empty")
        @Email(regexp = "^[\\w.+\\-]+@gitam\\.(in|edu)$", message = "Registration ID must be a valid Gitam email ID")
    String emailId;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 8, max = 32, message = "Password must be between 8 and 32 characters")
    String password;

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 3, max = 32, message = "Name must be between 3 and 32 characters")
    String name;

}
