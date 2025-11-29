import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.Date;
interface ok {
    void okt();

    // Static method
    static int add(int a, int b) {
        return a + b;
    }
}

@FunctionalInterface
interface okcon extends ok {
    static int add(int a, int b) {
        return a + b + 3;
    }
}

abstract class hmm{
    static int add(int a, int b) {
        return a + b;
    }
}

class hmmok extends hmm{
    static int add(int a, int b) {
        return a + b;
    }
}

public class Kk {
    public static void main(String[] args) {
        int factor = 2;
        Function<Integer, Integer> multiply = x -> x * factor;
        // factor = 3; // Error: phải effectively final

        List<List<Integer>> nested = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(3, 4),
                Arrays.asList(5, 6));

        nested.stream().flatMap(t -> t.stream()).forEach(t -> System.out.printf("%d ", t));
    }
}