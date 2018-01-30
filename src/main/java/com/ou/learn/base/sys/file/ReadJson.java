package com.ou.learn.base.sys.file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by weouyang on 09/08/2017.
 */
public class ReadJson {
    public static void main(String[] args) {
//        getTargetJson();
        getRaddJsonObject("/work/work-space/ouyang-eve/analytical-metadata/src/main/resources/metadata/variable/metadata.json");
    }

    public static void getRaddJsonObject(String url) {
        if (url == null || "".equals(url)) {
            System.out.println("ERROR----file url is empty!");
        }
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
            JsonArray fileArray = readJsonFile(url,gson);

            StringBuffer stringBuffer = new StringBuffer();
            for (JsonElement ob : fileArray){
                Object type = ob.getAsJsonObject().get("type");
                Object keyName = ob.getAsJsonObject().get("name");
                if(type == null || keyName == null)
                    continue;
                String key = type.toString().replaceAll("\"","");
                if (key.contains("EDGE")) {
                    String name = keyName.toString().replaceAll("\"","");
                    stringBuffer.append(name + "-");
                }
            }
            System.out.println("radd lists:"+stringBuffer);
        } catch (Exception e) {
            System.out.println("ERROR----EXCEPTION:"+e.getMessage());
            e.printStackTrace();
        }

    }

    public static void getTargetJson() {
        Set<String> set = new HashSet<String>();
        set.add("cp_accts_gm");
        set.add("cp_bad_accts_gm");
        set.add("cp_bad_dollar_gm");
        set.add("cp_bad_pmts_gm");
        set.add("cp_pmts_gm");

        getSpecialJsonObject("/Users/weouyang/Documents/java2eve/old_eve_var.json",true,set);
    }

    public static void getSpecialJsonObject(String url,boolean flag ,Set<String> matchSet) {
        if (url == null || "".equals(url)) {
            System.out.println("ERROR----file url is empty!");
        }
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
            JsonArray fileArray = readJsonFile(url,gson);

            JsonArray targetArray = new JsonArray();
            for (JsonElement ob : fileArray){
                Object keyName = ob.getAsJsonObject().get("name");
                if(keyName == null)
                    continue;
                String key = keyName.toString().replaceAll("\"","");
                if(flag){
                    if (matchSet != null && matchSet.contains(key)) {
                        targetArray.add(ob.getAsJsonObject());
                    }
                }else {
                    targetArray.add(ob.getAsJsonObject());
                }
            }
            System.out.println(gson.toJson(targetArray));
            System.out.println(" targetArray size:" + targetArray.size());
        } catch (Exception e) {
            System.out.println("ERROR----EXCEPTION:"+e.getMessage());
            e.printStackTrace();
        }
    }


    public static JsonArray readJsonFile(String url,Gson gson) {
        JsonArray fileArray = null;
        if (url == null || "".equals(url)) {
            System.out.println("ERROR----file url is empty!");
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(url));
            StringBuffer stringBuffer = new StringBuffer();
            String line = null;
            do {
                line = br.readLine();
                if( line != null)
                    stringBuffer.append(line);
            } while (line != null);
            br.close();
            fileArray = gson.fromJson(stringBuffer.toString().trim(), JsonArray.class);
        } catch (Exception e) {
            System.out.println("ERROR----EXCEPTION:"+e.getMessage());
            e.printStackTrace();
        }
        return fileArray;
    }


}
