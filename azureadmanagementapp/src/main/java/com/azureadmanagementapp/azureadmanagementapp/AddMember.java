package com.azureadmanagementapp.azureadmanagementapp;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AddMember {
  public AddMember(){
    
  }
  public void addMember(String groupName, String memberId) throws IOException {
    // RelatedGroups searchMember = new RelatedGroups();
    // if (searchMember.getRelatedGroups(memberId).contains(groupName)) {
    // System.out.println("Inside if statement");
    // return; // member already in group
    // }
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
    String requestBodyString = "{\r\n  \"@odata.id\": \"https://graph.microsoft.com/v1.0/directoryObjects/" + memberId
        + "\"\r\n}";
    RequestBody body = RequestBody.create(mediaType, requestBodyString);
    Request request = new Request.Builder()
        .url("https://graph.microsoft.com/v1.0/groups/" + id + "/members/$ref")
        .method("POST", body)
        .addHeader("Content-Type", "application/json")
        .addHeader("Authorization",
            "Bearer " + tokenReciever.getGraphToken())
        .build();
    Response response = client.newCall(request).execute();
    String s = response.peekBody(4096).string();
    System.out.println(s);

  }
}
