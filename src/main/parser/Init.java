package main.parser;

/**
 * Created by kalsi on 07/10/17.
 */
public class Init {

    public static void main(String[] args) {
        String json = "{\"hey\":[{\"then\":\"nothing\"}],\"sample\":{\"not\":\"ok\",\"ok\":\"rascal\"},\"color\":\"red\",\"size\":10,\"cost\":100.0,\"big\":true}";
        Parser.parse(json);
    }
}
