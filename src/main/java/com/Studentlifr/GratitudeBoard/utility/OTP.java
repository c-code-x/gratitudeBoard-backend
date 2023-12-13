package com.Studentlifr.GratitudeBoard.utility;

import lombok.Builder;
import lombok.Cleanup;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class OTP {
    private String value;
    private String emailId;
    private Timestamp generatedTime;
    @Builder.Default
    private int otpExpiryTimeMillis = 300000;

    public static OTP generateOTP(String emailId) {
        return OTP.builder()
                .value(RandomKeyGenerator.generateRandomString(6))
                .emailId(emailId)
                .generatedTime(new Timestamp(System.currentTimeMillis()))
                .build();
    }


    public boolean isExpired() {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        return currentTime.getTime() - generatedTime.getTime() > otpExpiryTimeMillis;
    }
}
