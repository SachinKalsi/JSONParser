package main.parser;

import java.util.ArrayList;

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
        int brackets = 0;
        Boolean result = null;
        for (int index = 0; index < parsedExpression.size(); index++) {
            Object token = parsedExpression.get(index);
            if (token instanceof Condition) {
                parsedExpression.set(index, jsonObject.evaluateCondition((Condition) token) ? "true" : "false");
            }
        }
        return findResult();
    }

    private Boolean findResult() {
        Boolean result = null;
        String operation = null;
        String[] tempStack = new String[parsedExpression.size()];
        int top = -1;
        for (int index = 0; index < parsedExpression.size(); index++) {
            String token = (String) parsedExpression.get(index);
            if (token.equals(")")) {
                while (top > -1) {
                    if (tempStack[top].equals("(")) {
                        tempStack[top] = result ? "true" : "false";
                        result = null;
                        break;
                    }
                    switch (tempStack[top]) {
                        case "true":
                        case "false":
                            if (result == null) {
                                result = tempStack[top] == "true" ? true : false;
                            } else {
                                if (operation == null) {
                                    System.out.println("Something went wrong!!");
                                    break;
                                }
                                if (operation.equals("AND")) {
                                    result = result && tempStack[top] == "true" ? true : false;
                                } else {
                                    result = result || tempStack[top] == "true" ? true : false;
                                }
                                operation = null;
                            }
                            break;
                        case "AND":
                            operation = "AND";
                            break;
                        case "OR":
                            operation = "OR";
                            break;
                    }
                    top--;
                }
            } else {
                tempStack[++top] = token;
            }
        }
        while (top != 0) {
            Boolean first = tempStack[top].equals("true") ? true : false;
            top--;
            operation = tempStack[top];
            top--;
            Boolean second = tempStack[top].equals("true") ? true : false;
            if (operation.equals("AND")) {
                tempStack[top] = (second && first) ? "true" : "false";
            } else if (operation.equals("OR")) {
                tempStack[top] = (second || first) ? "true" : "false";
            } else {
                System.out.println("Somthing went wrong!");
            }
        }
        System.out.println("final answer");
        System.out.println(tempStack[top]);


        return true;
    }

    private int skipNextCondition(ArrayList<Object> parsedExpression, int index, int brackets) {
        int finalBracketCount = brackets - 1;
        int i = index;
        for (; i < parsedExpression.size(); i++) {
            Object token = parsedExpression.get(i);
            if (token instanceof String) {
                if (token.equals("(")) {
                    brackets++;
                } else if (token.equals(")")) {
                    brackets--;
                }
                if (brackets == finalBracketCount) {
                    break;
                }
            }
        }
        return i;
    }
}
