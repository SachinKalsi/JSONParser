package main.parser;

/**
 * Created by kalsi on 09/10/17.
 */
public class InputData {
    String expression, jsonString;

    public void readInput() {
        expression = "\"( $cost == 100.0 AND ( $mattress.name == 'king' ) ) AND $size == 10\"";
        jsonString = "{\"color\":\"red\",\"size\":10,\"cost\":100.0,\"mattress\":{\"name\":\"king\"},\"big\":true,\"legs\":[{\"length\":4,\"arr\":[{\"p\":1}]}]}";
    }
}