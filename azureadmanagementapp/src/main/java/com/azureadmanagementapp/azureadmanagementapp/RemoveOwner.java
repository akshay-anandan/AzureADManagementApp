package com.azureadmanagementapp.azureadmanagementapp;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class RemoveOwner {
  public void removeOwner(String groupName, String memberId) throws IOException {
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
    MediaType mediaType = MediaType.parse("text/plain");
    RequestBody body = RequestBody.create(mediaType, "");
    Request request = new Request.Builder()
        .url(
            "https://graph.microsoft.com/v1.0/groups/" + id + "/owners/" + memberId + "/$ref")
        .method("DELETE", body)
        .addHeader("Authorization",
            "Bearer " + tokenReciever.getGraphToken())
        .build();
    client.newCall(request).execute();
  }
}
