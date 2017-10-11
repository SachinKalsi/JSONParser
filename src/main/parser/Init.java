package main.parser;

/**
 * Created by kalsi on 07/10/17.
 */
public class Init {
    public static void main(String[] args) {
        InputData inputData = new InputData();
        inputData.readInput();
        new Parser().process(inputData);
    }
}
