package com.Studentlifr.GratitudeBoard.utility;

import com.Studentlifr.GratitudeBoard.enumerators.ROLES;

public class CommonConstants {
    public static final String SECURITY_SCOPE_PREFIX = "SCOPE_";
    public static final String ADMIN_ROLE = SECURITY_SCOPE_PREFIX + ROLES.ADMIN;
    public static final String USER_ROLE = SECURITY_SCOPE_PREFIX+ ROLES.USER;
    public static final String ADMIN_URLS = "/admin/**";
    public static final String USER_URLS = "/user/**";
    private CommonConstants() {
    }
}
