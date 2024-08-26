package com.azureadmanagementapp.azureadmanagementapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RelatedGroups {
  public List<String> getRelatedGroups(String memberId) throws IOException {
    GraphToken tokenReciever = new GraphToken();

    OkHttpClient client = new OkHttpClient().newBuilder()
        .build();
    Request request = new Request.Builder()
        .url("https://graph.microsoft.com/v1.0/servicePrincipals/" + memberId + "/memberOf")
        .method("GET", null)
        .addHeader("Content-Type", "application/json")
        .addHeader("Authorization",
            "Bearer " + tokenReciever.getGraphToken())
        .build();
    Response response = client.newCall(request).execute();
    String json = response.peekBody(131072).string();

    ObjectMapper objectMapper = new ObjectMapper();
    List<String> displayNames = new ArrayList<>();

    try {
      JsonNode root = objectMapper.readTree(json);
      JsonNode valueNode = root.get("value");

      if (valueNode.isArray()) {
        for (JsonNode groupNode : valueNode) {
          JsonNode displayNameNode = groupNode.get("displayName");
          if (displayNameNode != null) {
            displayNames.add(displayNameNode.asText());
          }
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(displayNames);
      return displayNames;
    }

    return displayNames;
  }
}
