package com.ou.learn.work.func.factory;

import com.ou.learn.work.func.impl.BaseConverter;
import com.ou.learn.work.func.impl.RaddMetaConverter;
import com.ou.learn.work.func.impl.ReadingEdgeMetaConverter;
import com.ou.learn.work.func.impl.WritingEdgeMetaConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by weouyang on 26/01/2018.
 */
public class GenerateMetadataFactory {

    private  Map<MetaType,BaseConverter> map;

    public GenerateMetadataFactory(){
        init();
    }

    void init(){
        if (map == null) {
            map = new HashMap<>();
        }
        map.put(MetaType.RADD,new RaddMetaConverter());
        map.put(MetaType.READING_EDGE,new ReadingEdgeMetaConverter());
        map.put(MetaType.WRITING_DEGE,new WritingEdgeMetaConverter());
    }

    /**
     * return special metadat converter.
     * @param type
     * @return
     */
    public BaseConverter metaConverter(MetaType type) {
        return map.get(type);
    }

    public enum MetaType {
        RADD,READING_EDGE,WRITING_DEGE
    }
}
