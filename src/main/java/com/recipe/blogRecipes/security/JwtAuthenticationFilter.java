package com.recipe.blogRecipes.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired private JwtTokenProvider tokenProvider;
    @Autowired private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException , DisabledException{
        try {
            String jwt = getJwtFromRequest(request); //richiamo metodo finale. Tolgo la beare e tengo il token.

            if (StringUtils.hasText(jwt) /*&& tokenProvider.validateToken(jwt)*/) { //verifico che la string contenga qualcosa. Elaborazioni e controlli sulle stringhe.
                Long userId = tokenProvider.getUserIdFromJWT(jwt); // mi estrae l'ID. Con la classe DecoderJwt per decodificarlo

                /*
                    Note that you could also encode the user's username and roles inside JWT claims
                    and create the UserDetails object by parsing those claims from the JWT.
                    That would avoid the following database hit. It's completely up to you.
                 */
                UserDetails userDetails = customUserDetailsService.loadUserById(userId); //Istanzio UserDetail per per controlli. è un interfaccia

                // controllo che serve a verificare che a fronte di un token valido l'utente non sia stato nel frattempo disabilitato
                // come potrebbe succedere ad esempio con un ban.
                if(!userDetails.isEnabled())
                    throw new DisabledException("Account not enabled"); //eccezione presente nel pacchetto di SpringSecurity.

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            log.error("Could not set user authentication in security context", ex);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization"); //recupera il JWT dalla request
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
