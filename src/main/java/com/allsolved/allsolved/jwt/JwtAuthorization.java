package com.allsolved.allsolved.jwt;

import com.allsolved.allsolved.jwt.JwtTokenProvider;
import com.allsolved.allsolved.user.service.JwtService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@AllArgsConstructor
@Component
public abstract class JwtAuthorization {
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtService jwtService;


    /*public JwtAuthorization(JwtTokenProvider jwtTokenProvider, JwtService jwtService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtService = jwtService;
    }*/
    public JwtService getJwtService() { return jwtService; }
    public JwtTokenProvider getJwtTokenProvider() {
        return jwtTokenProvider;
    }

    public String getUserEmail(HttpServletRequest request) throws Exception {
        return jwtTokenProvider.getUserPk(jwtTokenProvider.getAccessToken(request));
    }

}
