package main.parser;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by kalsi on 09/10/17.
 */
public class ReadInput {

    public static void main(String[] args) {
        new ReadInput().readFile();
    }


    public static ArrayList<String> readFile() {
        ArrayList<String> input = new ArrayList<String>();
        input.add("\"( $cost == 100.0 AND ( $mattress.name == 'kingp' ) ) OR $size == 10\"");
        input.add("{\"color\":\"red\",\"size\":10,\"cost\":100.0,\"mattress\":{\"name\":\"king\"},\"big\":true,\"legs\":[{\"length\":4}]}");
        return input;
    }
}