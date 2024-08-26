package com.azureadmanagementapp.azureadmanagementapp;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GraphToken {
  final String client_id = "ed45019e-f93e-4d93-b892-4b9224315df2";
  final String client_secret = "PuU8Q~WM0rdEVdQPLNLCiWoGUVarl6LI6qMNmcwV";

  public String getGraphToken() throws IOException {
    OkHttpClient client = new OkHttpClient().newBuilder()
        .build();
    MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
    RequestBody body = RequestBody.create(
        "grant_type=client_credentials&client_id=" + client_id + "&client_secret=" + client_secret
            + "&resource=https://graph.microsoft.com/",
        mediaType);
    Request request = new Request.Builder()
        .url("https://login.microsoftonline.com/e78dc14e-76cd-400d-b760-935133352fe3/oauth2/token")
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
