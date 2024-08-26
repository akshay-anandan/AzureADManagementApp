package com.azureadmanagementapp.azureadmanagementapp;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class RemoveMember {
  public void removeMember(String groupName, String memberId) throws IOException {
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
    Request request = new Request.Builder()
        .url(
            "https://graph.microsoft.com/v1.0/groups/" + id
                + "/members/" + memberId + "/$ref")
        .method("DELETE", null)
        .addHeader("Authorization",
            "Bearer " + tokenReciever.getGraphToken())
        .build();
    client.newCall(request).execute();
  }
}
