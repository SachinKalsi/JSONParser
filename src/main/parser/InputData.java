package main.parser;

import java.util.*;
/**
 * Created by kalsi on 09/10/17.
 */
public class InputData {
    String expression, jsonString;

    /**
     * Valid expressions formats
     *  1. "$color == 'red'"
     *  2. "$mattress.name == 'king' AND $cost == 100.0"
     *  3. "NOT EXISTS $color"
     *  4. "( $cost == 100.0 AND ( $mattress.big == false ) ) OR $size == 100" etc
     *
     * Valid JSON Strings formats
     * 1. {"color":"red","size":10,"cost":100.0,"mattress":{"name":"king"},"big":true,"legs":[{"length":4}]}
     */
    public void readInput() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the expressio: ");
        expression = in.nextLine();
        System.out.println("Enter the jsonString: ");
        jsonString = in.nextLine();
    }
}