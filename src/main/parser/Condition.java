package main.parser;

/**
 * Created by kalsi on 09/10/17.
 */
public class Condition {
    private String type = null; // isExists Condition
    private String key;
    private String value = null;
    private String inputString;

    public Condition(String inputCondition) {
        this.inputString = inputString;
        parse();
    }

    private void parse() {
        if (inputString.charAt(0) == '$') {
            // check for equal condition, after removing dollar ($)
            inputString = inputString.substring(1);
            parseInputString();
        } else {
            // check for existence of a key in a json
            parseInputStringWithExistCondition();
        }
    }

    private void parseInputString() {
        String[] array = inputString.split(" == ");
        this.key = array[0];
        this.value = removeSingleQuotes(array[1]);
    }

    private String removeSingleQuotes(String s) {
        if (s.charAt(0) == '\'') {
            return s.substring(1, s.length() - 1);
        }
        return s;
    }

    private void parseInputStringWithExistCondition() {
        String[] array = inputString.split(" ");
        if (array[0].equals("NOT")) {
            type = "NOT EXISTS";
            setKey(array[2]);
        } else {
            type = "EXISTS";
            setKey(array[1]);
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
