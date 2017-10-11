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

    /**
     *
     * @param jsonString: json String
     * @return: JSON Object
     */
    private Map getJSONObject(String jsonString) {
        Map<String, Object> json = new HashMap<String, Object>();
        int i = 1; // skip the first semicolon
        String key, value;
        while (i < jsonString.length() - 1) {
            key = getKeyFromInputString(jsonString, i);
            i += key.length() + 1;
            value = getValueFromInputString(jsonString, i);
            i += value.length() + 1;
            key = JSONParseHelper.removeQuotes(key);
            insertKeyValue(key, value, json);
        }
        return json;
    }

    /**
     *
     * @param key
     * @param value
     * @param json: insert <key, value> pair to the json map by
     *            checking value type (json object, string, json array etc)
     */
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

    /**
     * converts string representation of an array to arrayList
     * @param stringRepresentationOfArray
     * @return: ArrayList of objects
     */
    private Object parseArray(String stringRepresentationOfArray) {
        ArrayList<Map<String, Object>> arrayList = new ArrayList<Map<String, Object>>();
        int i = 1, j;
        while (i < stringRepresentationOfArray.length() - 1) {
            j = retrieveObjectElement(stringRepresentationOfArray, i);
            arrayList.add(getJSONObject(stringRepresentationOfArray.substring(i, j)));
            i = j;
        }
        return arrayList;
    }

    /**
     * detect the object inside the array which is represented in string
     * @param stringRepresentationOfArray
     * @param index:  start index, from where search needs to be taken place
     * @return end index, where object ends
     */
    private int retrieveObjectElement(String stringRepresentationOfArray, int index) {
        int bracketsCount = 0;
        while (index < stringRepresentationOfArray.length() - 1) {
            if (stringRepresentationOfArray.charAt(index) == '{') {
                bracketsCount++;
            }
            if (stringRepresentationOfArray.charAt(index) == '}') {
                bracketsCount--;
            }
            if (bracketsCount == 0) {
                break;
            }
            index++;
        }
        return index + 1;
    }

    private String getValueFromInputString(String jsonString, int i) {
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

    /**
     * fetch array or object from input string
     * @param jsonString: input String
     * @param i: start index
     * @param isArray: true, if the object needs to be fetched is Array, otherwise false
     * @return Array or Object representation in string
     */
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

    private String getKeyFromInputString(String jsonString, int i) {
        return jsonString.substring(i, jsonString.length()).split(":")[0];
    }
}
