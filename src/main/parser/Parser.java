package main.parser;

import java.util.*;

/**
 * Created by kalsi on 09/10/17.
 */
public class Parser {
    public void process(InputData inputData) {
        String expression = inputData.expression;
        String jsonString = inputData.jsonString;
        // parse the json string
        JSONParser jsonParser = new JSONParser(jsonString);
        // parse the expression string
        ExpressionParser expressionParser = new ExpressionParser(expression);

        JSONObject jsonObject = new JSONObject(jsonParser.getJsonObject());
        // evaluate the expression & jsonObject
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator(jsonObject, expressionParser);
        Boolean result = expressionEvaluator.evaluate();
        // final result
        System.out.println("The result is " + result);
    }
}
