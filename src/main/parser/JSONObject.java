package main.parser;

import java.util.*;

/**
 * Created by kalsi on 09/10/17.
 */
public class JSONObject {
    private Object jsonObject = null;

    public JSONObject(Map<String, Object> jsonObject) {
        this.jsonObject = jsonObject;
    }

    public Boolean evaluateCondition(Condition condition) {
        Boolean isExistsCondition = condition.getCondition();
        if (isExistsCondition == null) {
            Object value = getValueFromKey(condition.getKey());
            return (value != null && (value.toString().equals(condition.getValue().toString())));
        }
        return isKeyExists(condition.getKey()) == isExistsCondition;
    }

    private Object getValueFromKey(String key) {
        Object value = jsonObject;
        String[] array = key.split("\\.");
        for (String keyString : array) {
            value = getValue(keyString, value);
            if (value instanceof ArrayList) {
                // the object is array, so assign null to value & return
                value = null;
            }
            if (value == null) {
                break;
            }
        }
        return value;
    }

    private Boolean isKeyExists(String key) {
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
        return ((Map<String, Object>) map).get(key);
    }
}
