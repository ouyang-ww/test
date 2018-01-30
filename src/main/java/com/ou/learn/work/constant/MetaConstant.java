package com.ou.learn.work.constant;

import com.sun.tools.javac.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by weouyang on 26/01/2018.
 */
public class MetaConstant {
    public static final long PIT = System.currentTimeMillis();
    public static Map<String, MetaKey> keyMap = new HashMap<>();
    static {
        keyMap.put("cust_id",new MetaKey("AccountNumber",true));
        keyMap.put("cust_id",new MetaKey("AccountNumber",true));
        keyMap.put("r_cust_id",new MetaKey("ReceiverAccountNumber",true));
        keyMap.put("account_number",new MetaKey("AccountNumber",true));
        keyMap.put("r_account_number",new MetaKey("AccountNumber",true));
        keyMap.put("actor_vid",new MetaKey("default_vid",true));
        keyMap.put("actor_ip",new MetaKey("default_enhanced_ip",true));

    }
    public static class MetaKey{
        public String name;
        public boolean variable;

        public MetaKey(String keyName,boolean isVariable) {
            this.name = keyName;
            this.variable = isVariable;
        }
    }

    public enum EdgeTypeEnum{
        LASTK("LastK"),
        DECAY("Decay"),
        SLIDINGWINDOW("SlidingWindow"),
        TSNC("TSNC"),
        CNT("CNT"),
        AMT("AMT"),
        MAX("MAX"),
        MIN("MIN"),
        _90DAYS("_90DAYS"),
        _72HOURS("_72HOURS");

        private String type;
        EdgeTypeEnum(String type){
            this.type = type;
        }

        public static EdgeTypeEnum getEdgeType(String name) {
            try {
                return EdgeTypeEnum.valueOf(name.toUpperCase());
            } catch (Exception e) {
                Assert.error("edge type unknow! name:"+name);
            }
            return null;
        }

        public String getType() {
            return type;
        }
    }
}
