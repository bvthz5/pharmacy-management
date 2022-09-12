package com.example.pharmacy.security.util;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.pharmacy.security.AccessTokenUserDetails;

/**
 *
 * @author nirmal
 */
public final class SecurityUtil {

    private SecurityUtil() {
    }

    public static Integer getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return null;
        }

        Object principal = auth.getPrincipal();
        if (!(principal instanceof AccessTokenUserDetails)) {
            return null;
        }

        return ((AccessTokenUserDetails) principal).userId;
    }
}