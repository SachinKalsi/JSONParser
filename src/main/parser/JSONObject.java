package main.parser;

import java.util.*;

/**
 * Created by kalsi on 09/10/17.
 */
public class JSONObject {
    Object jsonObject = null;

    public JSONObject(Map<String, Object> jsonObject) {
        this.jsonObject = jsonObject;
    }

    public Object getValueFromKey(String key) {
        Object value = jsonObject;
        String[] array = key.split("\\.");
        for (String keyString : array) {
            value = getValue(keyString, value);
            if (value instanceof ArrayList) {
                // thorow exception
                value = null;
                break;
            }
            if (value == null) {
                break;
            }
        }
        return value;
    }

    public Boolean isKeyExists(String key) {
        Object map = jsonObject;
        String[] array = key.split("\\.");
        for (String keyString : array) {
            if (((Map<String, Object>) map).containsKey(keyString)) {
                return true;
            }
            map = getValue(keyString, map);
            if (map == null) {
                break;
            }
        }
        return false;
    }

    private Object getValue(String key, Object map) {
        map = (Map<String, Object>) map;
        return ((Map<String, Object>) map).get(key);
    }
}
