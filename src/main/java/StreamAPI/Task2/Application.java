package StreamAPI.Task2;

import java.net.Inet4Address;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Application {
    void main(){
        Map<String, String> countries = Map.of("Russia", "Moscow", "USA", "Washington", "Germany", "Berlin");
        List<String> strings = List.of("abc", "dad", "mom", "parrot", "kid", "slowly", "book");
        List<Integer> values = List.of(1,2,3,4);
        getSortedByAlphabet(strings, "abdmoki").forEach(System.out::println);
    }

    public static List<int[]> getUniquePairs(Set<Integer> values, int target){
        return values.stream()
                .filter(x -> values.contains(target - x) && x < target - x)
                .map(x -> new int[] {x, target - x})
                .collect(Collectors.toList());
    }

    public static List<String> getSortedCapitals(Map<String, String> countries){
        return countries
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .toList();
    }

    public static List<String> getExactSortedString(List<String> strings, Character ch){
        return strings.stream()
                .filter(string -> string.startsWith(String.valueOf(ch)))
                .sorted(Comparator.comparingInt(String::length))
                .toList();
    }

    public static List<String> getBinary(List<Integer> values){
        return values.stream()
                .map(Integer::toBinaryString)
                .toList();
    }

    public static List<String> getSortedByAlphabet(List<String> strings, String alphabet){
        return strings.stream()
                .filter(word -> word.chars().allMatch(ch -> alphabet.indexOf(ch) != -1))
                        .sorted(Comparator.comparingInt(String::length))
                .toList();

    }
}