package com.allsolved.allsolved.common.errorhandler;

import com.slack.api.Slack;
import com.slack.api.model.Attachment;
import com.slack.api.model.Field;
import com.slack.api.webhook.Payload;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AutheniticationEntryPointHandler implements AuthenticationEntryPoint {


    private final Slack slackClient = Slack.getInstance();

    @Value("${slack.webhook.url}")
    private String webhookUrl;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String exception = (String) request.getAttribute("exception");
        ErrorCode errorCode;

        if (exception == null) {
            errorCode = ErrorCode.UNAUTHORIZEDException;
            sendSlackAlertErrorLog((Exception) request.getAttribute("e"), request);
            setResponse(response, errorCode);
            return;
        }

        if (exception.equals("NullPointerException")) {
            errorCode = ErrorCode.UNAUTHORIZEDException;
            sendSlackAlertErrorLog((Exception) request.getAttribute("e"), request);
            setResponse(response, errorCode);
            return;
        }


        if (exception.equals("PasswordNotFoundException")) {
            errorCode = ErrorCode.PasswordNotFoundException;
            sendSlackAlertErrorLog((Exception) request.getAttribute("e"), request);
            setResponse(response, errorCode);
            return;
        }

        if (exception.equals("ForbiddenException")) {
            errorCode = ErrorCode.ForbiddenException;
            sendSlackAlertErrorLog((Exception) request.getAttribute("e"), request);
            setResponse(response, errorCode);
            return;
        }

        //토큰이 만료된 경우
        if (exception.equals("ExpiredJwtException")) {
            errorCode = ErrorCode.ExpiredJwtException;
            sendSlackAlertErrorLog((Exception) request.getAttribute("e"), request);
            setResponse(response, errorCode);
            return;
        }

        //아이디 비밀번호가 다를 경우
        if (exception.equals("UsernameOrPasswordNotFoundException")) {
            errorCode = ErrorCode.UsernameOrPasswordNotFoundException;
            sendSlackAlertErrorLog((Exception) request.getAttribute("e"), request);
            setResponse(response, errorCode);
            return;
        }

    }

    private void setResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        JSONObject json = new JSONObject();
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        json.put("code", errorCode.getCode());
        json.put("message", errorCode.getMessage());
        response.getWriter().print(json);

    }

    // 슬랙 알림 보내는 메서드
    private void sendSlackAlertErrorLog(Exception e, HttpServletRequest request) {
        try {
            slackClient.send(webhookUrl, Payload.builder()
                    .text("서버 에러 발생! 백엔드 측의 빠른 확인 요망")
                    .attachments(List.of(generateSlackAttachment(e, request))).build());

        } catch (IOException slackError) {
            // slack 통신 시 발생한 예외에서 Exception을 던져준다면 재귀적인 예외가 발생합니다.
            // 따라서 로깅으로 처리하였고, 모카콩 서버 에러는 아니므로 `error` 레벨보다 낮은 레벨로 설정했습니다.
        }
    }

    // attachment 생성 메서드
    private Attachment generateSlackAttachment(Exception e, HttpServletRequest request) {
        String requestTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").format(LocalDateTime.now());
        String xffHeader = request.getHeader("X-FORWARDED-FOR");  // 프록시 서버일 경우 client IP는 여기에 담길 수 있습니다.
        return Attachment.builder()
                .color("ff0000")  // 붉은 색으로 보이도록
                .title(requestTime + " 발생 에러 로그")
        // Field도 List 형태로 담아주어야 합니다.
                .fields(List.of(
                                generateSlackField("Request IP", xffHeader == null ? request.getRemoteAddr() : xffHeader),
                                generateSlackField("Request URL", request.getRequestURL() + " " + request.getMethod()),
                                generateSlackField("Error Message", e.getMessage())
                        )
                )
                .build();
    }

    // Field 생성 메서드
    private Field generateSlackField(String title, String value) {
        return Field.builder()
                .title(title)
                .value(value)
                .valueShortEnough(false)
                .build();
    }
}