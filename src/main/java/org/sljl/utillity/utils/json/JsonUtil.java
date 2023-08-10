package org.sljl.utillity.utils.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Json工具类 基于Jackson封装的Json高效工具类
 *
 * @author L.Y.F
 */
public class JsonUtil {

    private static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        // 针对实体类中的null值,序列化时做忽略处理
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //针对Map中的null属性是否参与序列化
        OBJECT_MAPPER.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, true);
        // 反序列化时忽略未知属性
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 允许属性名称没有引号
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        // 允许单引号
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        OBJECT_MAPPER.registerModule(new Jdk8Module());
        OBJECT_MAPPER.registerModule(new JavaTimeModule());
        OBJECT_MAPPER.registerModule(new GuavaModule());
    }

    /**
     * 将对象转换为json字符串
     *
     * @param obj
     *
     * @return
     */
    public static String toJson(Object obj) {
        String jsonstr = null;
        try {
            jsonstr = OBJECT_MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonstr;
    }

    /**
     * 将json串转换成指定的对象 T
     *
     * @param json
     * @param objclass
     *
     * @return
     */
    public static <T> T jsonToT(String json, Class<T> objclass) {
        try {
            return OBJECT_MAPPER.readValue(json, objclass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json串转换成对象 Map
     *
     * @param json
     * @param kClass
     * @param vClass
     *
     * @return
     */
    public static <K, V> Map<K, V> jsonToMap(String json, Class<K> kClass, Class<V> vClass) {
        try {
            JavaType type = OBJECT_MAPPER.getTypeFactory().constructParametricType(Map.class, kClass, vClass);
            return OBJECT_MAPPER.readValue(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json串转换成对象 Map
     *
     * @param json
     * @param kClass
     * @param vClass
     *
     * @return
     */
    public static <K, V> List<Map<K, V>> jsonToList_Map(String json, Class<K> kClass, Class<V> vClass) {
        try {
            return OBJECT_MAPPER.readValue(json, new TypeReference<List<Map<K, V>>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json串转换成集合 List
     *
     * @param json
     * @param tClass
     *
     * @return
     */
    public static <T> List<T> jsonToList(String json, Class<T> tClass) {
        try {
            JavaType type = OBJECT_MAPPER.getTypeFactory().constructParametricType(List.class, tClass);
            return OBJECT_MAPPER.readValue(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String getJsonData(String json, String fieldname) {
        JsonNode rootNode = null;
        try {
            rootNode = OBJECT_MAPPER.readTree(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (rootNode == null) {
            return null;
        }
        String value = null;
        Iterator<String> names = rootNode.fieldNames();
        while (names.hasNext()) {
            String name = names.next();
            if (!fieldname.equals(name)) {
                continue;
            }
            JsonNode node = rootNode.path(name);
            if (node.isObject() || node.isArray() || node.isPojo()) {
                value = node.toString();
            } else if (node.isNumber()) {
                value = node.numberValue().toString();
            } else {
                value = node.textValue();
            }
            break;
        }
        return value;
    }

}
