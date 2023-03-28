package com.allsolved.allsolved.user.controller;

import com.allsolved.allsolved.jwt.JwtTokenProvider;
import com.allsolved.allsolved.user.service.JwtService;

import javax.servlet.http.HttpServletRequest;

public abstract class JwtController {
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtService jwtService;


    public JwtController(JwtTokenProvider jwtTokenProvider, JwtService jwtService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtService = jwtService;
    }
    public JwtService getJwtService() { return jwtService; }
    public JwtTokenProvider getJwtTokenProvider() {
        return jwtTokenProvider;
    }

    public String getCoUserEmail(HttpServletRequest request) throws Exception {
        return jwtTokenProvider.getUserPk(jwtTokenProvider.getAccessToken(request));
    }

}
