package main.parser;

import java.util.*;

/**
 * Created by kalsi on 07/10/17.
 */
public class Init {
    static Map<String, Object> json = null;

    public static void main(String[] args) {
        String jsonString = "{\"color\":\"red\",\"size\":10,\"cost\":100.0,\"mattress\":{\"name\":\"king\"},\"big\":true,\"legs\":[{\"length\":4}]}";
        json = Parser.parse(jsonString);
//        for (Map.Entry<String, Object> entry : json.entrySet()) {
//            System.out.print(entry.getKey() + " : ");
//            System.out.println(entry.getValue());
//        }
        System.out.println(findValueFromKey("cost") instanceof Double);
    }

    public static Object findValueFromKey(String key) {
        Object value = json;
        String[] array = key.split("\\.");
        for (String keyString : array) {
            value = getValue(keyString, value);
            if (value == null) {
                break;
            }
        }
        return value;
    }

    public static Object getValue(String key, Object map) {
        map = (Map<String, Object>) map;
        return ((Map<String, Object>) map).get(key);
    }
}
