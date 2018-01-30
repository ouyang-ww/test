package com.ou.learn.work.func;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.ou.learn.work.func.factory.GenerateMetadataFactory;
import com.ou.learn.work.tool.ExelParser;

/**
 * Created by weouyang on 26/01/2018.
 */
public class GenerateMetadataJson {

    public static ExelParser parser = new ExelParser("/Users/weouyang/Documents/doc/toEveVars.xlsx",0);
    public static ExelParser reading_edge_parser = new ExelParser("/Users/weouyang/Documents/doc/toEveVars.xlsx",2);

    public static void main(String[] args) {
        //print json arry
        printJson(GenerateMetadataFactory.MetaType.RADD,parser);
//        printJson(GenerateMetadataFactory.MetaType.READING_EDGE,reading_edge_parser);
    }

    public static void printJson(GenerateMetadataFactory.MetaType type,ExelParser parser){
        JsonArray jsonArray = listMetadataJson(type,parser);
        if (jsonArray != null) {
            System.out.println("jsonArray size:"+jsonArray.size());
            System.out.println(jsonToString(jsonArray));
        }
    }

    public static String jsonToString(JsonElement jsonArray) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(jsonArray);
    }

    public static JsonArray listMetadataJson(GenerateMetadataFactory.MetaType type,ExelParser parser) {
        GenerateMetadataFactory metadataFactory = new GenerateMetadataFactory();
        return metadataFactory.metaConverter(type).generateMetadataJson(parser);
    }
}
