package main.parser;

import java.util.*;

/**
 * Created by kalsi on 09/10/17.
 */
public class Parser {
    public void process(List<String> input) {
        String expression = input.get(0);
        String jsonString = input.get(1);
        JSONParser jsonParser = new JSONParser(jsonString);
        ExpressionParser expressionParser = new ExpressionParser(expression);
        JSONObject jsonObject = new JSONObject(jsonParser.getJsonObject());
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator(jsonObject,expressionParser);
        expressionEvaluator.evaluate();

    }
}
