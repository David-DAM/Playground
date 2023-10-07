import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Integer [] array = {1,2,3,4,5,6,7,8};
        List<Integer> numbers = Arrays.asList(array);
        
        Integer sum = numbers.stream().mapToInt(Integer::intValue).sum();
        System.out.println(sum);
    }
}