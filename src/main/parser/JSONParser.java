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
        jsonObject = getJSONObject(inputString);
    }

    private Map getJSONObject(String jsonString) {
        Map<String, Object> json = new HashMap<String, Object>();
        int i = 1; // skip the first semicolon
        String key, value;
        while (i < jsonString.length() - 1) {
            key = getKey(jsonString, i);
            i += key.length() + 1;
            value = getValue(jsonString, i);
            i += value.length() + 1;
            key = JSONParseHelper.removeQuotes(key);
            insertKeyValue(key, value, json);
        }
        return json;
    }

    private void insertKeyValue(String key, String value, Map<String, Object> json) {
        switch (value.charAt(0)) {
            case '{':
                json.put(key, getJSONObject(value));
                break;
            case '[':
                json.put(key, parseArray(value));
                break;
            case '"':
                json.put(key, JSONParseHelper.removeQuotes(value));
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
        }
    }

    private Object parseArray(String stringRepresentationOfArray) {
        ArrayList<Map<String, Object>> arrayList = new ArrayList<Map<String, Object>>();
        int i = 1, j;
        while (i < stringRepresentationOfArray.length() - 1) {
            j = getObjectElement(stringRepresentationOfArray, i);
            arrayList.add(getJSONObject(stringRepresentationOfArray.substring(i, j)));
            i = j;
        }
        return arrayList;
    }

    private int getObjectElement(String stringRepresentationOfArray, int index) {
        int flowerCount = 0;
        while (index < stringRepresentationOfArray.length() - 1) {
            if (stringRepresentationOfArray.charAt(index) == '{') {
                flowerCount++;
            }
            if (stringRepresentationOfArray.charAt(index) == '}') {
                flowerCount--;
            }
            if (flowerCount == 0) {
                break;
            }
            index++;
        }
        return index + 1;
    }

    private String getValue(String jsonString, int i) {
        if (jsonString.charAt(i) == '{') {
            return getObjectValue(jsonString, i, false);
        } else if (jsonString.charAt(i) == '[') {
            return getObjectValue(jsonString, i, true);
        }
        String value = jsonString.substring(i, jsonString.length()).split(",")[0];
        if (value.charAt(value.length() - 1) == '}') {
            return value.substring(0, value.length() - 1);
        }
        return value;
    }

    private String getObjectValue(String jsonString, int i, Boolean isArray) {
        Character openBracket = '{';
        Character closeBracket = '}';
        if (isArray) {
            openBracket = '[';
            closeBracket = ']';
        }
        int top = 0;
        int j = i;
        while (j < jsonString.length()) {
            if (jsonString.charAt(j) == openBracket) {
                top++;
            }
            if (jsonString.charAt(j) == closeBracket) {
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
