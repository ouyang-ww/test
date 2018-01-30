package com.ou.learn.work.func.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ou.learn.work.constant.MetaConstant;
import javafx.util.Pair;
import org.apache.commons.lang3.StringUtils;


/**
 * Created by weouyang on 26/01/2018.
 */
public class RaddMetaConverter extends BaseConverter{

    public static final String RADD_TYPE = "RADD";
    public static final String RADD_STATUS = "Implemented";
    public static final int RADD_VAR_LENGTH = 11;

    @Override
    public Pair<String, Object>[]  processParams(String[] params) {

        JsonObject createInfo = new JsonObject();
        JsonArray keys = new JsonArray();
        Pair<String, Object>[] pairs = new Pair[RADD_VAR_LENGTH];

        pairs[0] = new Pair<String, Object>(RaddFields.NAME.getFieldName(),params[RaddFields.NAME.getIndex()]);
        pairs[1] = new Pair<String, Object>(RaddFields.TYPE.getFieldName(),RADD_TYPE);
        pairs[2] = new Pair<String, Object>(RaddFields.STATUS.getFieldName(),RADD_STATUS);
        pairs[3] = new Pair<String, Object>(RaddFields.DESCRIPTION.getFieldName(),params[RaddFields.DESCRIPTION.getIndex()]);
        pairs[4] = new Pair<String, Object>(RaddFields.VALUE_TYPE.getFieldName(),defaultType(params[RaddFields.VALUE_TYPE.getIndex()]));
        pairs[5] = new Pair<String, Object>(RaddMetaConverter.RaddFields.DEFAULE_Value.getFieldName(),
            defaultValue(params[RaddFields.DEFAULE_Value.getIndex()],params[RaddFields.VALUE_TYPE.getIndex()]));
        pairs[6] = new Pair<String, Object>(RaddFields.CREATED_INFO.getFieldName(),createInfo);
        pairs[7] = new Pair<String, Object>(RaddFields.TABLE_NAME.getFieldName(),params[RaddFields.TABLE_NAME.getIndex()]);
        pairs[8] = new Pair<String, Object>(RaddFields.FIELD_NAME.getFieldName(),params[RaddFields.FIELD_NAME.getIndex()]);
        pairs[9] = new Pair<String, Object>(
            RaddFields.EMPTY_KEY_REPLACED.getFieldName(),stringToBoolean(params[RaddFields.EMPTY_KEY_REPLACED.getIndex()]));
        pairs[10] = new Pair<String, Object>(RaddFields.KEYS.getFieldName(),keys);

        createInfo.addProperty("reason","jira ticket id + ticket content");
        createInfo.addProperty("by","weouyang");
        createInfo.addProperty("date",MetaConstant.PIT);

        int[] keyIndexArray = new int[]{
            RaddFields.KEYS_1.getIndex(),
            RaddFields.KEYS_2.getIndex(),
            RaddFields.KEYS_3.getIndex(),
            RaddFields.KEYS_4.getIndex()};

        for (int keyIndex : keyIndexArray) {
            JsonArray keygroup = new JsonArray();
            String keyString = params[keyIndex];
            if (keyString == null || keyString.trim().length() == 0){
                continue;
            }
            keys.add(keygroup);
            String[] keyArray = keyString.trim().split(splitChar);
            for (String key : keyArray) {
                keygroup.add(getRaddKey(key));
            }
        }

        return pairs;
    }

    public JsonObject getRaddKey(String key) {
        if (StringUtils.isEmpty(key) || !MetaConstant.keyMap.containsKey(key.toLowerCase()))
            return null;
        MetaConstant.MetaKey metaKey = MetaConstant.keyMap.get(key.toLowerCase());
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name",metaKey.name);
        jsonObject.addProperty("variable",metaKey.variable);
        return jsonObject;
    }

    private enum RaddFields{
        NAME("name",0),
        TYPE("type",-1),
        STATUS("status",-1),
        DESCRIPTION("description",10),
        VALUE_TYPE("value_type",8),
        DEFAULE_Value("default_value",9),
        CREATED_INFO("created_info",-1),
        TABLE_NAME("table_name",1),
        FIELD_NAME("field_name",7),
        EMPTY_KEY_REPLACED("empty_key_replaced",6),
        KEYS("keys",-1),
        KEYS_1("keys",2),
        KEYS_2("keys",3),
        KEYS_3("keys",4),
        KEYS_4("keys",5);

        //represent RaddVariableMetadata Field name
        private String fieldName;
        //represent value array index,
        private int index;

        RaddFields(String fieldName,int index) {
            this.fieldName = fieldName;
            this.index = index;
        }

        public String getFieldName() {
            return this.fieldName;
        }
        public int getIndex() {
            return this.index;
        }
    }
}
