package hs.kr.dgsw.treamprototype.global.filter;

import hs.kr.dgsw.treamprototype.global.security.jwt.JwtProvider;
import hs.kr.dgsw.treamprototype.global.security.principle.AuthDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    private final AuthDetailsService authDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {
        String token = extractToken(request);
        if (token != null) {
            setAuthentication(token, request);
        }

        filterChain.doFilter(request, response);
    }

    private void setAuthentication(
            String token,
            HttpServletRequest request
    ) {
        UserDetails userDetails = authDetailsService.loadUserByUsername(jwtProvider.validateToken(token).get("email", String.class));
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String extractToken(
            HttpServletRequest request
    ) {
        String bearer = request.getHeader("Authorization");
        if (bearer != null && bearer.startsWith("Bearer ")) {
            return bearer.replace("Bearer ", "");
        }
        return null;
    }
}
