package main.parser;

import java.util.SortedMap;

/**
 * Created by kalsi on 07/10/17.
 */
public class Init {
    public static void main(String[] args) {
        InputData inputData = new InputData();
        inputData.readInput();
        try {
            new Parser().process(inputData);
        } catch (Exception e) {
            System.out.println("Error occurred while parsing");
            System.out.println(e.getMessage());
        }
    }
}
