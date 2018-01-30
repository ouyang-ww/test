package com.ou.learn.work.func.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ou.learn.work.exception.NoParamTypeException;
import com.ou.learn.work.tool.ExelParser;
import javafx.util.Pair;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weouyang on 26/01/2018.
 */
public abstract class BaseConverter {
    //default char is ','
    String splitChar = ",";

    /**
     * convert each metadata params translate into JsonObject,
     * and create a JsonArray collect all metada JsonObject.
     * @param parseTool
     * @return
     */
    public JsonArray generateMetadataJson(Object parseTool) {
        List<Pair<String,Object>[]> listParams =  convert(parseTool);
        if (listParams == null || listParams.size() == 0) {
            return null;
        }
        JsonArray varArray = new JsonArray();
        for (Pair<String,Object>[] params : listParams) {
            if (params == null || params.length == 0){
                continue;
            }
            JsonObject varInfo = new JsonObject();
            for (Pair<String, Object> param : params) {
                String key = param.getKey();
                Object value = param.getValue();
                if (value instanceof JsonElement) {
                    varInfo.add(key, (JsonElement) value);
                } else if (value instanceof String) {
                    varInfo.addProperty(key, (String) value);
                } else if (value instanceof Number) {
                    varInfo.addProperty(key, (Number) value);
                } else if (value instanceof Boolean) {
                    varInfo.addProperty(key, (Boolean) value);
                }else {
                    throw new NoParamTypeException("no this valueType : " + value.getClass());
                }
            }
            varArray.add(varInfo);
        }
        return varArray;
    }

    /**
     * convert each metadata params parseTool parsing into Pair<key,value>
     * @param parseTool
     * @return
     */
    public List<Pair<String, Object>[]> convert(Object parseTool) {
        if (!(parseTool instanceof ExelParser)) {
            return null;
        }
        return parserProcessParams((ExelParser)parseTool);
    }

    public List<Pair<String, Object>[]> parserProcessParams(ExelParser parseTool){
        List<String[]> raddList = parseTool.readVars();
        if (raddList == null | raddList.size() == 0) {
            return null;
        }

        List<Pair<String, Object>[]> listParams = new ArrayList<>();
        for (String[] params : raddList) {
            listParams.add(processParams(params));
        }
        return listParams;
    }

    public abstract Pair<String, Object>[] processParams(String[] params);

    public boolean stringToBoolean(String value) {
        if (StringUtils.isEmpty(value))
            return true;
        if (value.equalsIgnoreCase("true"))
            return true;
        else
            return false;

    }

    public Object defaultValue(String value,String valueType) {
        Object defaultValue = value;
        if (StringUtils.isEmpty(valueType))
            throw new NoParamTypeException("default value Type is empty");
        if (value == null || value.equalsIgnoreCase("null")){
            defaultValue = null;
        }

        if (valueType.equalsIgnoreCase("Long")) {
            defaultValue = Long.valueOf(value);
        } else if (valueType.equalsIgnoreCase("Double")) {
            defaultValue = Double.valueOf(value);
        } else if (valueType.equalsIgnoreCase("Integer")) {
            defaultValue = Integer.valueOf(value);
        }
        return defaultValue;
    }

    public String defaultType(String valueType) {
        String first = valueType.substring(0,1);
        String other = valueType.substring(1);
        return first.toUpperCase()+other.toLowerCase();

    }

    public String getSplitChar() {
        return splitChar;
    }

    public BaseConverter setSplitChar(String splitChar) {
        this.splitChar = splitChar;
        return this;
    }
}
