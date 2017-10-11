package main.parser;

import java.util.*;

/**
 * Created by kalsi on 09/10/17.
 */
public class ExpressionParser {
    String inputExpression = null;

    ArrayList<Object> tokens = new ArrayList<Object>();

    public ArrayList<Object> getTokens() {
        return tokens;
    }

    public ExpressionParser(String inputExpression) {
        this.inputExpression = inputExpression;
        parse();
    }

    /**
     * parse the input expression & split it into tokens & put it in ArrayList
     * if a 'condition' is found, condition object is made & it is treated as token
     */
    private void parse() {
        String[] inputExpressionArray = JSONParseHelper.removeQuotes(inputExpression).split(" ");
        StringBuilder sb = new StringBuilder();
        for (int index = 0; index < inputExpressionArray.length; index++) {
            String token = inputExpressionArray[index];
            if (token.charAt(0) == '$' || token.equals("NOT") || token.equals("EXISTS")) {
                sb.append(token);
                sb.append(inputExpressionArray[index + 1]);
                if (!token.equals("EXISTS")) {
                    sb.append(inputExpressionArray[index + 2]);
                    index++;
                }
                index++;
                tokens.add(new Condition(sb.toString()));
                sb = new StringBuilder();
            } else if (isLogicalOperator(token)) {
                tokens.add(token);
            }
        }
    }

    private boolean isLogicalOperator(String token) {
        return token.equals("AND") || token.equals("OR") || token.equals("NOT") || token.equals("(") || token.equals(")");
    }
}
