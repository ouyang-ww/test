package com.ou.learn.work.tool;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by weouyang on 12/01/2018.
 */
public class AnalyzeMetadataJson {

    public static void main(String[] args) throws Exception {
        listMetaNames();
    }

    //list all eve variable name to special fileUrl
    public static void listMetaNames() throws Exception {
        String metadataJson = getJson();
        List<String> listName = new ArrayList<>();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonArray jsonArray = gson.fromJson(metadataJson, JsonArray.class);
        for (JsonElement element : jsonArray) {
            JsonObject obj = element.getAsJsonObject();
            String name = obj.get("name").getAsString();
            listName.add(name);
        }

        writeFile(listName);
    }

    public static String getJson() throws Exception {
        String fileUrl = "metadata/variable/metadata.json";
        InputStream in = AnalyzeMetadataJson.class.getClassLoader().getResourceAsStream(fileUrl);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        do {
            line = reader.readLine();
            stringBuilder.append(line);
        } while (line != null);
        return stringBuilder.toString();
    }

    public static void writeFile(List<String> list) throws IOException {
        String fileUrl = "metaName.json";
        File file = new File(fileUrl);
        if (!file.exists())
            file.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        for (String ss : list)
                writer.write(ss+"\n");
    }
}
