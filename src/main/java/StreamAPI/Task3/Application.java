package StreamAPI.Task3;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Application {
    void main(){
        List<UserAction> actions = List.of(new UserAction(1, "Vlad", ActionType.POST, LocalDate.now(), "Its my first post!"),
                new UserAction(2, "Egor", ActionType.POST, LocalDate.now(), "I've posted about my job!"),
                new UserAction(3, "Sveta", ActionType.LIKE, LocalDate.now(), "Liking Egor's post"),
                new UserAction(4, "Polina", ActionType.COMMENT, LocalDate.now(), "Commenting Vlad's post"),
                new UserAction(5, "Alex", ActionType.SHARE, LocalDate.now(), "Sharing Egor's post"));
    }

    public static List<String> getMostNActiveUsers(List<UserAction> actions, int userCount){
        return actions.stream()
                .collect(Collectors.groupingBy(UserAction::getName, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(userCount)
                .map(Map.Entry::getKey)
                .toList();
    }

    public static List<String> getMostNPopularTopics(List<UserAction> actions, int topicCount){
        return actions.stream()
                .filter(action -> action.getActionType().equals(ActionType.COMMENT) || action.getActionType().equals(ActionType.POST))
                .flatMap(userAction -> Arrays.stream(userAction.getContext().split(" ")))
                .map(String::trim)
                .map(String::toLowerCase)
                .filter(string -> string.startsWith("#"))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(topicCount)
                .map(Map.Entry::getKey)
                .toList();
    }

    public static List<String> getMostActiveUsersByComments(List<UserAction> actions, int userCount){
        return actions.stream()
                .filter(action -> action.getDate().isAfter(LocalDate.now().minusMonths(1)))
                .filter(action -> action.getActionType() == ActionType.COMMENT)
                .collect(Collectors.groupingBy(UserAction::getName, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(userCount)
                .map(Map.Entry::getKey)
                .toList();
    }

    public static Map<ActionType, Double> getActionTypePerсentage(List<UserAction> actions){
        double totalActionTypeCount = actions.size();

        return actions.stream()
                .collect(Collectors.groupingBy(
                        UserAction::getActionType,
                        Collectors.collectingAndThen(
                                Collectors.counting(),
                                count ->  count / totalActionTypeCount * 100
                        )
                ));

    }
}