package main.parser;

import java.util.*;

/**
 * Created by kalsi on 09/10/17.
 */
public class Parser {
    public void process(InputData inputData) {
        String expression = inputData.expression;
        String jsonString = inputData.jsonString;

        JSONParser jsonParser = new JSONParser(jsonString);
        ExpressionParser expressionParser = new ExpressionParser(expression);
        JSONObject jsonObject = new JSONObject(jsonParser.getJsonObject());

        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator(jsonObject, expressionParser);
        expressionEvaluator.evaluate();

    }
}
