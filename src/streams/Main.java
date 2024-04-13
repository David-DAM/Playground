package streams;

import oop.Father;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public class Main {
    public static void main(String[] args) {


    }

    /**
     * Max value of occurrences by the first letter of a name
     */
    private static void maxValueOfOccurrencesFirstLetterOfName() {
        List<String> nameList = List.of("David", "Dante", "Alberto", "Carolina", "Estrella", "Coral");

        Map.Entry<String, Long> maxValue = nameList.stream()
                .collect(Collectors.groupingBy(x -> x.substring(0,1), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get();

        System.out.println(maxValue);
    }

    /**
     * Map from list of objects to a list concatenated of his attribute list values
     */
    private static void concatenatedList() {
        Father father = new Father("Pepe",38);
        father.setPhoneNumbers(List.of("1","2","3"));

        Father father2 = new Father("Pepe",38);
        father2.setPhoneNumbers(List.of("4","5","6"));

        List<Father> fatherList = new ArrayList<>(List.of(father,father2));

        List<String> stringList = fatherList.stream()
                .map(x -> Optional.ofNullable(x.getPhoneNumbers()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .flatMap(Collection::stream)
                .toList();

        System.out.println(stringList);
    }

    /**
     * Remove all duplicate elements from a list using
     */
    private static void removeAllDuplicates() {
        Integer [] numbers = {5,6,7,8,5};

        List<Integer> integers = Arrays.stream(numbers)
                .distinct()
                .toList();

        System.out.println(integers);
    }

    /**
     *  Join string
     */
    private static void joinString() {

        List<String> joinExample = Arrays.asList("1","2","3","4");

        String join = String.join("-", joinExample);

        System.out.println(join);
    }

    /**
     * Skip and limit example
     */
    private static void skipAndLimit() {
        List<Integer> limit = IntStream.rangeClosed(1, 10)
                .boxed()
                .skip(1)
                .limit(7)
                .toList();

        System.out.println(limit);
    }

    /**
     * calculate the average of a list of integers
     */
    private static void calculateAverage() {
        int [] numbersAverage = {1,2,5,7};
        double average = Arrays.stream(numbersAverage).average().getAsDouble();
        System.out.println("Average: "+average);
    }

    /**
     * Find all elements from an array who starts with 1
     */
    private static void findAllElementsWhoStartsWith() {
        int [] numbersArray = { 1, 34, 25, 15, 56, 111, 25 };

        List<Integer> allElementsWhoStartsWith = Arrays.stream(numbersArray)
                .boxed()
                .filter(x -> x.toString().startsWith("1"))
                .toList();
        System.out.println(allElementsWhoStartsWith);
    }

    /**
     * Find the longest string from an array
     */
    private static void longestString() {

        String [] strArray = {"i", "love", "programming", "in", "java"};

        String longestString = Arrays.stream(strArray)
                .reduce((x, y) -> x.length() > y.length() ? x : y)
                .get();

        System.out.println(longestString);
    }

    /**
     * Find second-highest number from an array
     */
    private static void secondHighestNumber() {

        int [] numbers = {1,3,14,24,35,56,68,23};

        Integer sencondHighestNumber = Arrays.stream(numbers)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .get();

        System.out.println(sencondHighestNumber);
    }

    /**
     * Find first non-repeated occurrence of a string
     */
    private static void firstNonRepeatedOccurrence() {

        String words = "iloveprogramming";

        String firstNonRepeatedOcurrence = Arrays.stream(words.split(""))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(x -> x.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .get();

        System.out.println(firstNonRepeatedOcurrence);
    }

    /**
     * Find the repeated occurrence of a string
     */
    private static void findRepeatedOccurrence() {

        String words = "iloveprogramming";

        List<String> repeatedOcurrence = Arrays.stream(words.split(""))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(x -> x.getValue() > 1)
                .map(Map.Entry::getKey)
                .toList();

        System.out.println(repeatedOcurrence);
    }

    /**
     * Count all occurrence of a letter
     */
    private static void countAllOccurrence() {

        String words = "iloveprogramming";

        Map<String, Long> allOcurrence = Arrays.stream(words.split(""))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println(allOcurrence);
    }

    /**
     * Sum all the numbers grouped by his identity
     */
    private static void sumOfNumbersGrouped() {

        int [] numbers = {1,3,14,24,35,56,68,23};

        Map<Integer, Integer> sumOfNumbersGrouped = Arrays.stream(numbers)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(),Collectors.summingInt(Integer::intValue)));

        System.out.println(sumOfNumbersGrouped);
    }
}
