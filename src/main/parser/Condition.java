package main.parser;

/**
 * Created by kalsi on 09/10/17.
 */
public class Condition {
    private Boolean isExistsCondition = null; // isExists Condition
    private String key;
    private String value = null;
    private String inputString;

    public Condition(String inputString) {
        this.inputString = inputString;
        parse();
    }

    public Boolean getCondition() {
        return isExistsCondition;
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
        String[] array = inputString.split("==");
        this.key = array[0];
        this.value = JSONParseHelper.removeQuotes(array[1]);
    }


    private void parseInputStringWithExistCondition() {
        String[] array = inputString.split("EXISTS");
        isExistsCondition = true; // exists
        if (array[0].equals("NOT")) { // not exists
            isExistsCondition = false;
        }
        setKey(array[1].substring(1));
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

}
