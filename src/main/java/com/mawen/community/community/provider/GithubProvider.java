package com.mawen.community.community.provider;

import com.alibaba.fastjson.JSON;
import com.mawen.community.community.dto.AccessTokenDto;
import com.mawen.community.community.dto.GithubUserDto;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDto accessTokenDto) {
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDto));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String[] split = string.split("&");
            String[] split1 = split[0].split("=");
            return split1[1];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUserDto getUser (String accessToken) {
        OkHttpClient client = new OkHttpClient();
        System.out.println(accessToken);
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUserDto githubUserDto = JSON.parseObject(string, GithubUserDto.class);
            return githubUserDto;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
