package cn.dreamchan.common.core.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;

/**
 * json 转换工具类
 *
 * @author DreamChan
 */
public final class JsonUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 将 POJO 转为 JSON
     */
    public static <T> String toJson(T obj) {
        String json;
        try {
            json = OBJECT_MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return json;
    }

    /**
     * 将 JSON 转为 POJO
     */
    public static <T> T toObject(String json, Class<T> type) {
        T pojo;
        try {
            pojo = OBJECT_MAPPER.readValue(json, type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return pojo;
    }

    /**
     * 将 JSON 转为 List
     */
    public static <T> T toList(String json, TypeReference<T> type) {
        try {
            // 忽略不存在的元素
            OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return OBJECT_MAPPER.readValue(json, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <K, V> HashMap<K, V> toMap(String json, Class<K> keyType, Class<V> valueType) {
        try {
            // 忽略不存在的元素
            OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return OBJECT_MAPPER.readValue(json, new TypeReference<HashMap<K, V>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
