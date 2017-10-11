package main.parser;

import java.util.*;

/**
 * Created by kalsi on 09/10/17.
 */
public class ExpressionEvaluator {
    JSONObject jsonObject;
    ArrayList<Object> parsedExpression;

    public ExpressionEvaluator(JSONObject jsonObject, ExpressionParser expressionParser) {
        this.jsonObject = jsonObject;
        this.parsedExpression = expressionParser.getParsedExpression();
    }

    public Boolean evaluate() {
        for (int index = 0; index < parsedExpression.size(); index++) {
            Object token = parsedExpression.get(index);
            if (token instanceof Condition) {
                parsedExpression.set(index, JSONParseHelper.getStringValue(jsonObject.evaluateCondition((Condition) token)));
            }
        }
        return computeResult();
    }

    private Boolean computeResult() {
        Stack stack = new Stack(new String[parsedExpression.size()]);
        for (int index = 0; index < parsedExpression.size(); index++) {
            String token = (String) parsedExpression.get(index);
            parseToken(token, stack);
        }
        stack.evaluate();
        System.out.println("final answer");
        System.out.println(JSONParseHelper.getBooleanValue(stack.getTop()));
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
