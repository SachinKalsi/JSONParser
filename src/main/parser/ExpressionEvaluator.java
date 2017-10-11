package main.parser;

import java.util.*;

/**
 * Created by kalsi on 09/10/17.
 */
public class ExpressionEvaluator {
    JSONObject jsonObject;
    ArrayList<Object> tokens;

    public ExpressionEvaluator(JSONObject jsonObject, ExpressionParser expressionParser) {
        this.jsonObject = jsonObject;
        this.tokens = expressionParser.getTokens();
    }

    /**
     * evaluate the tokens against jsonObject
     * if token is a condition object, evaluate & store it the tokens array
     */
    public Boolean evaluate() {
        for (int index = 0; index < tokens.size(); index++) {
            Object token = tokens.get(index);
            if (token instanceof Condition) {
                Boolean result = jsonObject.evaluateCondition((Condition) token);
                tokens.set(index, JSONParseHelper.getStringValue(result));
            }
        }
        return computeResult();
    }

    /**
     * parse tokens array to find the final result with the help of Stack
     * @return
     */
    private Boolean computeResult() {
        Stack stack = new Stack(new String[tokens.size()]);
        for (int index = 0; index < tokens.size(); index++) {
            String token = (String) tokens.get(index);
            parseToken(token, stack);
        }
        stack.evaluate();
        return JSONParseHelper.getBooleanValue(stack.getTop());
    }

    private void parseToken(String token, Stack stack) {
        if (token.equals(")")) {
            Boolean result = null;
            String operation = null;
            while (stack.top > -1) {
                if (stack.getTop().equals("(")) {
                    stack.setTop(JSONParseHelper.getStringValue(result));
                    return;
                }
                if (stack.getTop().equals("true") || stack.getTop().equals("false")) {
                    Boolean tempResult = JSONParseHelper.getBooleanValue(stack.getTop());
                    if (result == null) {
                        result = tempResult;
                    } else {
                        result = operation.equals("AND") ? (result && tempResult) : (result || tempResult);
                        operation = null;
                    }
                } else {
                    operation = stack.getTop();
                }
                stack.top--;
            }
        } else {
            stack.top++;
            stack.setTop(token);
        }
    }


}
