package com.example.pharmacy.security;
import static com.example.pharmacy.security.AccessTokenUserDetailsService.PURPOSE_ACCESS_TOKEN;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

/**
 *
 * @author nirmal
 */
public class AccessTokenProcessingFilter extends AbstractPreAuthenticatedProcessingFilter {

    private static final Pattern AUTH_PATTERN = Pattern.compile("Pharmacy ([0-9a-f]+)");

 
    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        String authHeader = request.getHeader("authorization");
        if (authHeader == null) {
            return null;
        }

        Matcher matcher = AUTH_PATTERN.matcher(authHeader);
        if (!matcher.matches()) {
            return null;
        }

        return matcher.group(1);
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return PURPOSE_ACCESS_TOKEN;
    }
}