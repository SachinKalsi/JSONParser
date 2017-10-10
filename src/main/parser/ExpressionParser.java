package main.parser;

import java.util.*;

/**
 * Created by kalsi on 09/10/17.
 */
public class ExpressionParser {
    String inputExpression = null;

    ArrayList<Object> arrayList = new ArrayList<Object>();

    public ArrayList<Object> getParsedExpression() {
        return arrayList;
    }

    public ExpressionParser(String inputExpression) {
        this.inputExpression = inputExpression;
        parse();
    }

    private void parse() {
        Boolean result = false;
        String[] inputExpressionArray = inputExpression.substring(1, inputExpression.length() - 1).split(" ");
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
                arrayList.add(new Condition(sb.toString()));
                sb = new StringBuilder();
            } else if (isLogicalOperator(token)) {
                arrayList.add(token);
            }
        }
    }

    private boolean isLogicalOperator(String token) {
        return token.equals("AND") || token.equals("OR") || token.equals("NOT") || token.equals("(") || token.equals(")");
    }
}
