package com.epam.mjc.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class FileReader {

    public Profile getDataFromFile(File file) {
        StringBuilder builder = new StringBuilder();

        try (BufferedReader buffer = new BufferedReader(new java.io.FileReader(file))) {
            String str;
            while ((str = buffer.readLine()) != null) {
                builder.append(str).append("\n");
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        String[] content = builder.toString().split("\n");
        Map<String, String> contentMap = new HashMap<>();
        for(String s: content){
            String[] pair = s.split(":");
            contentMap.put(pair[0], pair[1].trim());
        }

        return  new Profile(
                contentMap.get("Name"),
                Integer.parseInt(contentMap.get("Age")),
                contentMap.get("Email"),
                Long.parseLong(contentMap.get("Phone"))
        );
    }
}