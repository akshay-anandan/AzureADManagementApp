package com.azureadmanagementapp.azureadmanagementapp;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GraphToken {
  final String client_id = "";
  final String client_secret = "";

  public String getGraphToken() throws IOException {
    OkHttpClient client = new OkHttpClient().newBuilder()
        .build();
    MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
    RequestBody body = RequestBody.create(
        "grant_type=client_credentials&client_id=" + client_id + "&client_secret=" + client_secret
            + "&resource=https://graph.microsoft.com/",
        mediaType);
    Request request = new Request.Builder()
        .url("https://login.microsoftonline.com/**********/oauth2/token")
        .method("POST", body)
        .addHeader("grant_type", "")
        .addHeader("Content-Type", "application/x-www-form-urlencoded")
        .build();
    Response response = client.newCall(request).execute();
    String s = response.peekBody(4096).string();
    int tokenLocation = s.indexOf("access_token") + 15;
    String token = s.substring(tokenLocation, s.length() - 2);
    return token;
  }
}
