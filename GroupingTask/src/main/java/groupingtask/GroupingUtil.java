package groupingtask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class GroupingUtil {
    public static <K> Map<K, List<User>> groupUsers(List<User> users, Function<User, K> classifier){
        if (users == null)
                throw new NullPointerException("The list shouldn't be null");
        if (users.isEmpty())
            return Collections.emptyMap();
        if (classifier == null)
            throw new NullPointerException("classifier must not be null");

        Map<K, List<User>> res = new HashMap<>();

        for(User user : users){
            res.computeIfAbsent(classifier.apply(user), k -> new ArrayList<>()).add(user);
        }

        return res;
    }
}