package functions;
import java.util.function.Function;

public class Uppercase implements Function<String, String> {

    public String apply(String input) {
        return input.toUpperCase();
    }
}