package streams;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        //Count the all occurrence of a string
        String words = "iloveprogramming";

        Map<String, Long> allOcurrence = countAllOcurrence(words);
        //System.out.println(allOcurrence);

        //Find the repeated occurrence of a string
        List<String> repeatedOcurrence = findRepeatedOcurrence(words);
        //System.out.println(repeatedOcurrence);

        //Find first non-repeated occurrence of a string
        String firstNonRepeatedOcurrence = firstNonRepeatedOcurrence(words);
        //System.out.println(firstNonRepeatedOcurrence);

        //Find second-highest number from an array
        int [] numbers = {1,3,14,24,35,56,68,23};

        Integer sencondHighestNumber = sencondHighestNumber(numbers);
        //System.out.println(sencondHighestNumber);

        //Find the longest string from an array
        String [] strArray = {"i", "love", "programming", "in", "java"};

        String longestString = longestString(strArray);
        //System.out.println(longestString);

        //Find all elements from an array who starts with 1
        int [] numbersArray = { 1, 34, 25, 15, 56, 111, 25 };

        List<Integer> allElementsWhoStartsWith = findAllElementsWhoStartsWith(numbersArray);
        //System.out.println(allElementsWhoStartsWith);

        //Join example
        List<String> joinExample = Arrays.asList("1","2","3","4");
        String join = String.join("-", joinExample);
        //System.out.println(join);

        //Skip and limit example
        List<Integer> limit = IntStream.rangeClosed(1, 10)
                .boxed()
                .skip(1)
                .limit(7)
                .toList();
        //System.out.println(limit);

        Map<Integer, Integer> sumOfNumbersGrouped = sumOfNumbersGrouped(numbersArray);
        System.out.println(sumOfNumbersGrouped);

    }

    private static List<Integer> findAllElementsWhoStartsWith(int[] numbersArray) {
        return Arrays.stream(numbersArray)
                .boxed()
                .filter(x -> x.toString().startsWith("1"))
                .toList();
    }

    private static String longestString(String[] strArray) {
        return Arrays.stream(strArray)
                .reduce((x, y) -> x.length() > y.length() ? x : y)
                .get();
    }

    private static Integer sencondHighestNumber(int[] numbers) {
        return Arrays.stream(numbers)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst().get();
    }

    private static String firstNonRepeatedOcurrence(String words) {
        return Arrays.stream(words.split(""))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(x -> x.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .get();
    }

    private static List<String> findRepeatedOcurrence(String words) {
        return Arrays.stream(words.split(""))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(x -> x.getValue() > 1)
                .map(Map.Entry::getKey)
                .toList();
    }

    private static Map<String, Long> countAllOcurrence(String words) {
        return Arrays.stream(words.split(""))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private static Map<Integer, Integer> sumOfNumbersGrouped(int[] numbers) {
        return Arrays.stream(numbers)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(),Collectors.summingInt(Integer::intValue)));
    }
}
