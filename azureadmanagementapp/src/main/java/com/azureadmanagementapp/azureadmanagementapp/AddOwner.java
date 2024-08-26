package com.azureadmanagementapp.azureadmanagementapp;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AddOwner {
  public void addOwner(String groupName, String memberId) throws IOException {
    GraphToken tokenReciever = new GraphToken();
    SearchGroups search = new SearchGroups();
    String id = "";
    if (search.doesExactExist(groupName)) {
      id = search.getID(groupName);
    } else {
      return;
    }
    OkHttpClient client = new OkHttpClient().newBuilder()
        .build();
    MediaType mediaType = MediaType.parse("application/json");
    String json = String.format("{\n  \"@odata.id\": \"https://graph.microsoft.com/v1.0/servicePrincipals/%s\"\n}",
        memberId);
    RequestBody body = RequestBody.create(mediaType, json);
    Request request = new Request.Builder()
        .url("https://graph.microsoft.com/v1.0/groups/" + id + "/owners/$ref")
        .method("POST", body)
        .addHeader("Content-type", "application/json")
        .addHeader("Authorization",
            "Bearer " + tokenReciever.getGraphToken())
        .build();
    Response response = client.newCall(request).execute();
    String s = response.peekBody(4096).string();
    System.out.println(s);
  }
}
