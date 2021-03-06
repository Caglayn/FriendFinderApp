package com.bilgeadam.config.security;

import com.bilgeadam.utility.JwtEncodeDecode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenManager jwtTokenManager;

    @Autowired
    private JwtUserDetail jwtUserDetail;

    @Autowired
    private JwtEncodeDecode jwtEncodeDecode;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization"); //Bearer Token

        /**
         * 1. Authorization var mı yokmu??
         * 2. Bearer var mı yokmu??
         * 3. Kişi önceden oturum açmış mı????
         */
        if (authorizationHeader != null &&
                authorizationHeader.startsWith("Bearer ") &&
                SecurityContextHolder.getContext().getAuthentication()==null) {
            /***
             * 7. karakterden sonrası bizim token bilgimiz olacak.
             */
            String token = authorizationHeader.substring(7);
            boolean isValid = jwtTokenManager.validateToken(token);
            if (isValid){
                Optional<String> profileId = jwtTokenManager.getProfileId(token);
                if (profileId.isPresent()){
                    UserDetails user = jwtUserDetail.loadUserProfileId(profileId.get());
                    if (user != null){
                        /**
                         * Eğer Kullanıcı bilgileri doğru ise bize verilen spring oturum kullanıcısını
                         * Session oluşturarak bu session içine gömeriz.
                         */
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }
        }

        /**
         * Tüm isterler işlendiktenn sonra, gelen request olduğu gibi gönderilir.
         */
        filterChain.doFilter(request, response);
    }
}
