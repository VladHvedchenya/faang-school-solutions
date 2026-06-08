package StreamAPI.Task3;

import java.time.LocalDate;

public class UserAction {
    private final int userId;
    private final String userName;
    private final ActionType actionType;
    private final LocalDate date;
    private final String content;

    public UserAction(int userId, String userName, ActionType actionType, LocalDate date, String content) {
        //String null and empty checks
        this.userId = userId;
        this.userName = userName;
        this.actionType = actionType;
        this.date = date;
        this.content = content;
    }

    public String getName(){
        return userName;
    }

    public String getContext(){
        return content;
    }

    public ActionType getActionType(){
        return actionType;
    }

    public LocalDate getDate(){
        return date;
    }
}
