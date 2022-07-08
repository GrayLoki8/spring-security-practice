package ua.grayloki8.spring.springsecuritypractice.config;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ua.grayloki8.spring.springsecuritypractice.security.JWTUtil;
import ua.grayloki8.spring.springsecuritypractice.services.PersonDetailsService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JWTFilter extends OncePerRequestFilter {

   private JWTUtil jwtUtil;
   private PersonDetailsService detailsService;

    public JWTFilter(JWTUtil jwtUtil, PersonDetailsService detailsService) {
        this.jwtUtil = jwtUtil;
        this.detailsService = detailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        System.out.println("here");
        System.out.println(authorization);
        if (authorization!=null && !authorization.isBlank() && authorization.startsWith("Bearer ")){
            String jwt=authorization.substring(7);
            System.out.println(jwt);
            if (jwt.isBlank()){
                response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                        "Invalid JWT Token in Bearer Header");
            }else{
                try{
                String username = jwtUtil.validateTokenAndRetrieveClaim(jwt);
                    System.out.println(username);
                    UserDetails userDetails = detailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(userDetails,
                        userDetails.getPassword(),userDetails.getAuthorities());
                if (SecurityContextHolder.getContext().getAuthentication()==null){
                    System.out.println("here2");
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }}catch (JWTVerificationException e){
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Invalid JWT Token");
                }
            }
        }
        filterChain.doFilter(request,response);
    }
}
