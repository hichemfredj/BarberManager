package cal.barbermanager.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * Json web token authentication filter
 * <p>
 * Filter used to retrieve json web tokens from http request headers. If there
 * is a token that can be decoded successfully, it will be parsed into a jwt
 * authentication and be set inside of the security context.
 *
 * @see JwtAuthentication
 * @see JwtProvider
 * @see OncePerRequestFilter
 * @see SecurityContextHolder
 *
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    //
    // Dependencies
    //

    /**
     * Json web token provider
     */
    @Autowired
    private JwtProvider JwtProvider;

    //
    // Services
    //

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {

            // Get encoded token
            final String token = getJwt(request);

            // Parse jwt token
            final DecodedJWT decodedToken = JwtProvider.verify(token);

            if (decodedToken != null) {

                final JwtAuthentication authentication = new JwtAuthentication(decodedToken);

                SecurityContextHolder.getContext().setAuthentication(authentication);

            }

        } catch (JWTVerificationException e) {

            // failed to verify jwt token (No authentication set)

            //e.printStackTrace();
        }

        filterChain.doFilter(request, response);
    }


    private String getJwt(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ","");
        }
        return null;
    }

}
