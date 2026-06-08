package StreamAPI.Task1;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class Application {
    void main(){
        List<Integer> arr = List.of(10,5,20,15,3,7);
        List<String> strings = List.of("cba", "cbabab", "aa", "cjfsjh", "gfsk", "cgscbbb", "fjacbbsk");

        transformsString(strings).forEach(System.out::println);
    }

    public static int evenSum(List<Integer> nums){
        return nums.stream()
                .filter(n -> n % 2 == 0)
                .reduce(0, Integer::sum);
    }

    public static int maxElements(List<Integer> nums){
        return nums.stream()
                .max(Integer::compare)
                .orElseThrow(() -> new IllegalArgumentException("The list is empty or null"));
    }

    public static double averageSum(List<Integer> nums){
        return nums.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElseThrow(() -> new IllegalArgumentException("The list is empty or null"));
    }

    public static long countStringStartsWithSymbol(List<String> strings, char ch){
        return strings.stream()
                .filter(string -> string.startsWith(String.valueOf(ch)))
                .count();
    }

    public static List<String> getListWithExactSubString(List<String> strings, String substring){
        return strings.stream()
                .filter(string -> string.contains(substring))
                .toList();
    }

    public static List<String> getSortedByLength(List<String> strings){
        return strings.stream()
                .sorted(Comparator.comparingInt(String::length))
                .toList();
    }

    public static boolean isAllMathes(List<Integer> nums, Predicate<Integer> filter){
        return nums.stream().allMatch(filter);
    }

    public static int lowestInt(List<Integer> nums){
        return nums.stream()
                .filter(n -> n > 6)
                .mapToInt(Integer::intValue)
                .min()
                .orElseThrow(() -> new IllegalArgumentException("Нет таких элементов"));
    }

    public static List<Integer> transformsString(List<String> strings){
        return strings.stream()
                .map(String::length)
                .toList();
    }
}