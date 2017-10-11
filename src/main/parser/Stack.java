package main.parser;

/**
 * Created by kalsi on 11/10/17.
 */
public class Stack {

    int top;
    String[] arrayStack;

    public Stack(String[] arrayStack) {
        this.top = -1;
        this.arrayStack = arrayStack;
    }

    public String getTop() {
        return arrayStack[top];
    }

    public void setTop(String s) {
        arrayStack[top] = s;
    }

    public void evaluate() {
        String operation;
        while (top != 0) {
            Boolean first = JSONParseHelper.getBooleanValue(arrayStack[top]);
            top--;
            operation = arrayStack[top];
            top--;
            Boolean second = JSONParseHelper.getBooleanValue(arrayStack[top]);
            Boolean tempResult = operation.equals("AND") ? (second && first) : (second || first);
            setTop(JSONParseHelper.getStringValue(tempResult));
        }
    }
}
