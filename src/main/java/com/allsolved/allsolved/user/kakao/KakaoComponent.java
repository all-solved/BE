package com.allsolved.allsolved.user.kakao;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Component;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

@Component
public class KakaoComponent {

    public String getAccessTocken(String authorize_code) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=bc712decd5a530ca7387c2ab07618a92");
            sb.append("&redirect_uri=http://semtle.catholic.ac.kr:8081/allso/sns/login");
            sb.append("&code=" + authorize_code);
            bw.write(sb.toString());
            bw.flush();

            // 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();

            // 요청에 의한 JSON 타입 response 메시지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while((line = br.readLine()) != null) {
                result += line;
            }

            //Gson 라이브러르에 포함된 클래스로 json파싱
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();


        }catch(Exception e) {
            e.printStackTrace();
        }

        return access_Token;
    }

    public HashMap<String, Object> getUserInfo(String access_Token) {
        HashMap<String, Object> userInfo = new HashMap<>();
        String reqURL = "https://kapi.kakao.com/v2/user/me";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            conn.setRequestProperty("Authorization", "Bearer " + access_Token);

            int responseCode = conn.getResponseCode();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while((line = br.readLine()) != null) {
                result += line;
            }

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

            String alsoPassword = element.getAsJsonObject().get("id").getAsString();
            String alsoEmail = kakao_account.getAsJsonObject().get("email").getAsString();

            userInfo.put("alsoEmail", alsoEmail);
            userInfo.put("alsoPassword", alsoPassword);


        }catch(Exception e) {
            e.printStackTrace();
        }

        return userInfo;
    }
}