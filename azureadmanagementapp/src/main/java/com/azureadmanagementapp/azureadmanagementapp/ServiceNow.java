package com.azureadmanagementapp.azureadmanagementapp;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ServiceNow {

  final private String authString = "";

  public String getAuth() {
    return authString;
  }

  public void createChangeTicket(String ticketType, String name) throws IOException {
    OkHttpClient client = new OkHttpClient().newBuilder()
        .build();
    MediaType mediaType = MediaType.parse("application/json");
    RequestBody body = RequestBody.create(mediaType, "{\"name\":\"" + ticketType + " " + name + "\"}");
    Request request = new Request.Builder()
        .url("*********")
        .method("POST", body)
        .addHeader("Content-Type", "application/json")
        .addHeader("Authorization", "Basic " + getAuth())
        .build();
    Response response = client.newCall(request).execute();
    String s = response.peekBody(4096).string();
  }

}
