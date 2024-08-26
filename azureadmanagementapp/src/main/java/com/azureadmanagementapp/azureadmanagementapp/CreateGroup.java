package com.azureadmanagementapp.azureadmanagementapp;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class CreateGroup {
  public void createGroup(String groupName) throws IOException {
    GraphToken tokenReciever = new GraphToken();
    OkHttpClient client = new OkHttpClient().newBuilder()
        .build();
    MediaType mediaType = MediaType.parse("application/json");
    RequestBody body = RequestBody.create(mediaType,
        "{\r\n  \"description\": \"Created by Management Tool\",\r\n  \"displayName\": \"" + groupName
            + "\",\r\n  \"mailEnabled\": false,\r\n  \"mailNickname\": \"" + groupName
            + "\",\r\n  \"securityEnabled\": true\r\n}");
    Request request = new Request.Builder()
        .url("https://graph.microsoft.com/v1.0/groups")
        .method("POST", body)
        .addHeader("Content-Type", "application/json")
        .addHeader("Authorization",
            "Bearer " + tokenReciever.getGraphToken())
        .build();
    client.newCall(request).execute();
  }

  public void createIfNew(String groupName) throws IOException {
    SearchGroups search = new SearchGroups();
    if (!search.doesExactExist(groupName)) {
      createGroup(groupName);
    }
  }
}
