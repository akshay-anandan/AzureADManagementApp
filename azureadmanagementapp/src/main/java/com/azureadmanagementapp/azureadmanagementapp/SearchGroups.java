package com.azureadmanagementapp.azureadmanagementapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SearchGroups {

        // display name uses contains logic - needs to work for exact display names
        public boolean doesExist(String groupName) throws IOException {
                GraphToken tokenReciever = new GraphToken();
                OkHttpClient client = new OkHttpClient().newBuilder()
                                .build();
                Request request = new Request.Builder()
                                .url("https://graph.microsoft.com/v1.0/groups?$search=\"displayName:" + groupName
                                                + "\"&ConsistencyLevel=eventual")
                                .method("GET", null)
                                .addHeader("Content-Type", "application/json")
                                .addHeader("Authorization",
                                                "Bearer " + tokenReciever.getGraphToken())
                                .build();
                Response response = client.newCall(request).execute();
                String json = response.peekBody(131072).string();
                System.out.println(json);
                if (json.contains("\"id\":")) {
                        return true;
                } else
                        return false;
        }

        public boolean doesExactExist(String groupName) throws IOException {
                GraphToken tokenReciever = new GraphToken();
                OkHttpClient client = new OkHttpClient().newBuilder()
                                .build();
                Request request = new Request.Builder()
                                .url("https://graph.microsoft.com/v1.0/groups?$search=\"displayName:" + groupName
                                                + "\"&ConsistencyLevel=eventual")
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
                }

                for (int i = 0; i < displayNames.size(); i++) {
                        // System.out.println(displayNames.get(i));
                        if (displayNames.get(i).toLowerCase().equals(groupName.toLowerCase())) {
                                return true;
                        }
                }
                return false;
        }

        public String getID(String groupName) throws IOException {
                GraphToken tokenReciever = new GraphToken();
                OkHttpClient client = new OkHttpClient().newBuilder()
                                .build();
                Request request = new Request.Builder()
                                .url("https://graph.microsoft.com/v1.0/groups?$search=\"displayName:" + groupName
                                                + "\"&ConsistencyLevel=eventual")
                                .method("GET", null)
                                .addHeader("Content-Type", "application/json")
                                .addHeader("Authorization",
                                                "Bearer " + tokenReciever.getGraphToken())
                                .build();
                Response response = client.newCall(request).execute();
                String json = response.peekBody(2048).string();
                int start = json.indexOf("id") + 5;
                String cutStart = json.substring(start);
                int end = cutStart.indexOf(",");
                String id = cutStart.substring(0, end - 1);
                return id;

        }

}
