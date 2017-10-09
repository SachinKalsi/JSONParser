package main.parser;

import java.util.*;

/**
 * Created by kalsi on 07/10/17.
 */
public class JSONParser {
    public Map<String, Object> getJsonObject() {
        return jsonObject;
    }

    private Map<String, Object> jsonObject = null;
    private String inputString = null;

    public JSONParser(String inputString) {
        this.inputString = inputString;
        startParse();
    }

    private void startParse() {
        jsonObject = parse(inputString);
    }

    private Map parse(String jsonString) {
        Map<String, Object> json = new HashMap<String, Object>();
        int i = 1;
        int j = 0;
        String key, value;
        while (i < jsonString.length() - 1) {
            key = getKey(jsonString, i);
            i += key.length() + 1;
            value = getValue(jsonString, i);
            i += +value.length() + 1;
            key = key.substring(1, key.length() - 1);
            switch (value.charAt(0)) {
                case '{':
                    json.put(key, parse(value));
                    break;
                case '[':
                    json.put(key, parseArray(value));
                    break;
                case '"':
                    json.put(key, value.substring(1, value.length() - 1));
                    break;
                case 't':
                case 'f':
                    json.put(key, Boolean.parseBoolean(value));
                    break;
                default:
                    if (value.indexOf('.') >= 0) {
                        json.put(key, Double.parseDouble(value));
                    } else {
                        json.put(key, Integer.parseInt(value));
                    }
                    break;
            }
        }
        return json;
    }

    private Object parseArray(String value) {
        ArrayList<Map<String, Object>> arrayList = new ArrayList<Map<String, Object>>();
        int i = 1;
        int j;
        int flowerCount;
        while (i < value.length() - 1) {
            flowerCount = 0;
            j = i;
            while (j < value.length() - 1) {
                if (value.charAt(j) == '{') {
                    flowerCount++;
                }
                if (value.charAt(j) == '}') {
                    flowerCount--;
                }
                if (flowerCount == 0) {
                    break;
                }
                j++;
            }
            arrayList.add(parse(value.substring(i, j + 1)));
            i = j + 1;
        }
        return arrayList;
    }

    private String getValue(String jsonString, int i) {
        if (jsonString.charAt(i) == '{') {
            return getObjectValue(jsonString, i);
        } else if (jsonString.charAt(i) == '[') {
            return getArrayValue(jsonString, i);
        }
        String value = jsonString.substring(i, jsonString.length()).split(",")[0];
        if (value.charAt(value.length() - 1) == '}') {
            return value.substring(0, value.length() - 1);
        }
        return value;
    }

    private String getArrayValue(String jsonString, int i) {
        int top = 0;
        int j = i;
        while (j < jsonString.length()) {
            if (jsonString.charAt(j) == '[') {
                top++;
            }
            if (jsonString.charAt(j) == ']') {
                top--;
            }
            if (top == 0) {
                break;
            }
            j++;
        }
        return jsonString.substring(i, j + 1);
    }

    private String getObjectValue(String jsonString, int i) {
        int top = 0;
        int j = i;
        while (j < jsonString.length()) {
            if (jsonString.charAt(j) == '{') {
                top++;
            }
            if (jsonString.charAt(j) == '}') {
                top--;
            }
            if (top == 0) {
                break;
            }
            j++;
        }
        return jsonString.substring(i, j + 1);
    }

    private String getKey(String jsonString, int i) {
        return jsonString.substring(i, jsonString.length()).split(":")[0];
    }
}
