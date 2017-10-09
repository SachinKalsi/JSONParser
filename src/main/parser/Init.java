package main.parser;

import java.util.*;

/**
 * Created by kalsi on 07/10/17.
 */
public class Init {
//    static Map<String, Object> json = null;

    public static void main(String[] args) {
        List<String> input = ReadInput.readFile();
        new Parser().process(input);
    }

}
