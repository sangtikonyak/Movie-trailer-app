package com.capstone.WatchLater.jwtfilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        ServletOutputStream outputStream=response.getOutputStream();

        String authHeader=request.getHeader("Authorization");

        if(authHeader==null || !authHeader.startsWith("Bearer")){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            outputStream.print("Invalid token");
            outputStream.close();
        }
        else {
            String token=authHeader.substring(7);
            Claims claims= Jwts.parser().setSigningKey("mysecretkey").parseClaimsJws(token).getBody();
            request.setAttribute("email",claims.getSubject());
            System.out.println(claims);
            filterChain.doFilter(request,response);
        }
    }
}
