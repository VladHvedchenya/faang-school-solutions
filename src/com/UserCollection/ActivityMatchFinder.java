package com.UserCollection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ActivityMatchFinder {
    public Map<User, String> findHobbyLovers(List<User> users, Set<String> desireActivities){
        Map<User, String> res = new HashMap<>();

        for (User user : users){
            Set<String> userActivities = user.getActivities();

            for (String activity : desireActivities){
                if (userActivities.contains(activity)){
                    res.put(user, activity);
                    break;
                }
            }
        }

        return res;
    }
}