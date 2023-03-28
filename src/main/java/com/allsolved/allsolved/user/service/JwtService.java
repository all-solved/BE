package com.allsolved.allsolved.user.service;

import com.allsolved.allsolved.errorhandler.AllSolvedException;
import com.allsolved.allsolved.errorhandler.AllsoResponse;
import com.allsolved.allsolved.errorhandler.AuthenticationCustomException;
import com.allsolved.allsolved.errorhandler.ErrorCode;
import com.allsolved.allsolved.jwt.JwtTokenProvider;
import com.allsolved.allsolved.user.entity.RefreshToken;
import com.allsolved.allsolved.user.entity.Token;
import com.allsolved.allsolved.user.repository.RefreshTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class JwtService {
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AllsoResponse login(HttpServletRequest request, Map<String, String> user, String userAgent) {

        AllsoResponse.ResponseMap result = null;
        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.get("alsoEmail"), user.get("alsoPassword"))
            );

            Token token = getAlsoEmail(user.get("alsoEmail"), userAgent);

            result = new AllsoResponse.ResponseMap(200, "accessToken", token.getAccessToken());
            result.setResponseData("refreshToken", token.getRefreshToken());
            result.setResponseData("key", token.getKey());
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("exception", "UsernameOrPasswordNotFoundException");
            throw new AuthenticationCustomException(ErrorCode.UsernameOrPasswordNotFoundException);
        }

        return result;
    }

    private Token getAlsoEmail(String alsoEmail, String userAgent) {
        Token token = jwtTokenProvider.createToken(alsoEmail);
        //RefreshToken을 DB에 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .keyId(token.getKey())
                .refreshToken(token.getRefreshToken())
                .userAgent(userAgent).build();

        Optional<RefreshToken> tokenOptional = refreshTokenRepository.findByKeyId(alsoEmail);

        if(tokenOptional.isPresent()) {
            if(!tokenOptional.get().getUserAgent().equals(userAgent)) {
                refreshTokenRepository.deleteByKeyId(alsoEmail);
                refreshTokenRepository.save(refreshToken);
            }
        }else {
            refreshTokenRepository.save(refreshToken);
        }
        return token;
    }


    public AllsoResponse newAccessToken(RefreshToken refreshToken) {
        if(refreshToken.getRefreshToken() != null) {
            String newToken = jwtTokenProvider.validateRefreshToken(refreshToken);
            return new AllsoResponse.ResponseMap(200, "accessToken", newToken);
        }else {
            throw new AllSolvedException(ErrorCode.ReLogin);
        }
    }
}
