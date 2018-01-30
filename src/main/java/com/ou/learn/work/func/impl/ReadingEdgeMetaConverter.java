package com.ou.learn.work.func.impl;

import com.google.gson.JsonObject;
import com.ou.learn.work.constant.MetaConstant;
import com.ou.learn.work.tool.ExelParser;
import com.sun.tools.javac.util.Assert;
import javafx.util.Pair;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by weouyang on 26/01/2018.
 */
public class ReadingEdgeMetaConverter extends BaseConverter{
    public static final String READING_EDGE_TYPE = "READING_EDGE";
    public static final String READING_EDGE_STATUS = "Implemented";

    @Override
    public Pair<String, Object>[] processParams(String[] params) {
        String edgeType = validateEdgeType(params[ReadingEdgeFields.EDGE_TYPE.getIndex()]);
        if (StringUtils.isEmpty(edgeType)) {
            System.out.println("edgeType is empty!");
            return null;
        }

        JsonObject createInfo = new JsonObject();
        List<Pair<String, Object>> pairs = new ArrayList<>(16);

        pairs.add(new Pair<String, Object>(ReadingEdgeFields.NAME.getFieldName(), params[ReadingEdgeFields.NAME.getIndex()]));
        pairs.add(new Pair<String, Object>(ReadingEdgeFields.TYPE.getFieldName(),READING_EDGE_TYPE));
        pairs.add(new Pair<String, Object>(ReadingEdgeFields.STATUS.getFieldName(),READING_EDGE_STATUS));
        pairs.add(new Pair<String, Object>(ReadingEdgeFields.DESCRIPTION.getFieldName(),params[ReadingEdgeFields.DESCRIPTION.getIndex()]));
        pairs.add(new Pair<String, Object>(ReadingEdgeFields.DEFAULE_Value.getFieldName(),
            defaultValue(params[ReadingEdgeFields.DEFAULE_Value.getIndex()],params[ReadingEdgeFields.VALUE_TYPE.getIndex()])));
        pairs.add(new Pair<String, Object>(ReadingEdgeFields.CREATED_INFO.getFieldName(),createInfo));
        pairs.add(new Pair<String, Object>(ReadingEdgeFields.CONTAINER_TYPE.getFieldName(),params[ReadingEdgeFields.CONTAINER_TYPE.getIndex()]));
        pairs.add(new Pair<String, Object>(ReadingEdgeFields.IS_LEGACY.getFieldName(),
            stringToBoolean(params[ReadingEdgeFields.IS_LEGACY.getIndex()])));
        pairs.add(new Pair<String, Object>(ReadingEdgeFields.CONTAINER_KEY.getFieldName(),getContainerKey(params[ReadingEdgeFields.CONTAINER_KEY.getIndex()])));
        pairs.add(new Pair<String, Object>(ReadingEdgeFields.CORRESPONDING_VARIABLE.getFieldName(),params[ReadingEdgeFields.CORRESPONDING_VARIABLE.getIndex()]));
        pairs.add(new Pair<String, Object>(ReadingEdgeFields.EDGE_TYPE.getFieldName(),edgeType));
        pairs.add(new Pair<String, Object>(ReadingEdgeFields.VALUE_TYPE.getFieldName(),defaultType(params[ReadingEdgeFields.VALUE_TYPE.getIndex()])));

        createInfo.addProperty("reason","jira ticket id + ticket content");
        createInfo.addProperty("by","weouyang");
        createInfo.addProperty("date", MetaConstant.PIT);

        if (edgeType.equalsIgnoreCase("LastK")) {
            addLastkParam(pairs,params);
        } else if (edgeType.equalsIgnoreCase("Decay")) {
            addDecayParam(pairs,params);
        } else if (edgeType.equalsIgnoreCase("SlidingWindow")) {
            addSlidingWindowParam(pairs,params);
        }

        return pairs.toArray(new Pair[pairs.size()]);
    }

    public void addLastkParam(List<Pair<String, Object>> pairs,String[] params){
        pairs.add(new Pair<String, Object>(ReadingEdgeFields.LASTK_TIME_PERIOD.getFieldName(),getTimePeriod(params)));
    }

    public void addDecayParam(List<Pair<String, Object>> pairs,String[] params) {
        pairs.add(new Pair<String, Object>(ReadingEdgeFields.DECAY_AGGREGATION_TYPE.getFieldName(),
            validateEdgeType(params[ReadingEdgeFields.DECAY_AGGREGATION_TYPE.getIndex()])));
        pairs.add(new Pair<String, Object>(ReadingEdgeFields.DECAY_FACTOR.getFieldName(),params[ReadingEdgeFields.DECAY_FACTOR.getIndex()]));
        pairs.add(new Pair<String, Object>(ReadingEdgeFields.DECAY_ROUND.getFieldName(),stringToBoolean(params[ReadingEdgeFields.DECAY_ROUND.getIndex()])));

    }

    public void addSlidingWindowParam(List<Pair<String, Object>> pairs,String[] params) {
        String start_time = null;
        String end_time = null;
        JsonObject period = new JsonObject();
        period.addProperty("start_time",start_time);
        period.addProperty("end_time",end_time);
        pairs.add(new Pair<String, Object>(ReadingEdgeFields.SLIDINGWINDOW_AGGREGATION_TYPE.getFieldName(),
            validateEdgeType(params[ReadingEdgeFields.SLIDINGWINDOW_AGGREGATION_TYPE.getIndex()])));
        pairs.add(new Pair<String, Object>(ReadingEdgeFields.SLIDINGWINDOW_WINDOW_SIZE.getFieldName(),
            validateEdgeType(params[ReadingEdgeFields.SLIDINGWINDOW_WINDOW_SIZE.getIndex()])));
        pairs.add(new Pair<String, Object>(ReadingEdgeFields.SLIDINGWINDOW_TIME_PERIOD.getFieldName(),getTimePeriod(params)));
    }

    public String getContainerKey(String containerKey) {
        if (StringUtils.isEmpty(containerKey) || !MetaConstant.keyMap.containsKey(containerKey.toLowerCase()))
            Assert.error("containerKey is not contained in MetaConstant.keyMap! containerKey:"+containerKey);
        return MetaConstant.keyMap.get(containerKey.toLowerCase()).name;
    }

    /**
     * get Lastk timePeriod or SlidingWindow`s
     * @param params
     * @return
     */
    public JsonObject getTimePeriod(String[] params) {
        String start_time = null;
        String end_time = null;
        String timePeriod = params[ReadingEdgeFields.LASTK_TIME_PERIOD.getIndex()];
        if (StringUtils.isEmpty(timePeriod)) {
            String varName = params[ReadingEdgeFields.NAME.getIndex()];
            if (StringUtils.isEmpty(varName)) {
                Assert.error("no varName!");
            }
            String[] substrs = varName.split("_");
            start_time = substrs[substrs.length-2];
            if (!validateTime(start_time)){
                start_time = substrs[substrs.length-1];
            }else{
                end_time = substrs[substrs.length-1];
            }
        }else {
            String[] times = timePeriod.split(",");
            start_time = times[0];
            if (times.length >1) {
                end_time = times[1];
            }
        }

        if (!validateTime(start_time,end_time)){
            Assert.error("timePeriod validate failed: start_time:"+start_time+" end_time:"+end_time);
        }

        if (StringUtils.isEmpty(start_time)){
            Assert.error("start_time is empty!");
        }

        JsonObject period = new JsonObject();
        period.addProperty("start_time",start_time);
        period.addProperty("end_time",end_time);
        return period;
    }

    public boolean validateTime(String... times) {
        boolean re = true;
        try {
            for (String time : times) {
                if (!StringUtils.isEmpty(time)) {
                    Long.valueOf(time.substring(0, time.length() - 1));
                    String timeUnit = time.substring(time.length() - 1);
                    Set<String> set = new HashSet<>();
                    set.add("s");
                    set.add("m");
                    set.add("h");
                    set.add("d");
                    if (!set.contains(timeUnit)) {
                        re = false;
                        break;
                    }
                }
            }
        } catch (NumberFormatException e) {
            re = false;
        }
        return re;
    }

    public static String validateEdgeType(String edgeType) {
        MetaConstant.EdgeTypeEnum typeEnum = MetaConstant.EdgeTypeEnum.getEdgeType(edgeType);
        if (typeEnum == null){
            return null;
        }
        return typeEnum.getType();
    }

    public enum ReadingEdgeFields{
        NAME("name",0),
        TYPE("type",-1),
        STATUS("status",-1),
        DESCRIPTION("description",1),
        DEFAULE_Value("default_value",3),
        CREATED_INFO("created_info",-1),
        CONTAINER_TYPE("container_type",7),
        IS_LEGACY("is_legacy",9),
        CONTAINER_KEY("container_key",2),
        CORRESPONDING_VARIABLE("corresponding_variable",8),
        EDGE_TYPE("edge_type",6),
        VALUE_TYPE("value_type",5),
        LASTK_TIME_PERIOD("time_period",10),
        DECAY_AGGREGATION_TYPE("aggregation_type",11),
        DECAY_FACTOR("factor",12),
        DECAY_ROUND("round",13),
        SLIDINGWINDOW_AGGREGATION_TYPE("aggregation_type",11),
        SLIDINGWINDOW_WINDOW_SIZE("window_size",14),
        SLIDINGWINDOW_TIME_PERIOD("time_period",10);

        //represent RaddVariableMetadata Field name
        private String fieldName;
        //represent value array index,
        private int index;

        ReadingEdgeFields(String fieldName,int index) {
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
