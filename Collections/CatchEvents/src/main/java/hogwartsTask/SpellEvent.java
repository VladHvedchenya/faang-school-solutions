package hogwartsTask;

import java.util.Objects;

enum EventType{
    DEFENCE,
    TRANSFORMATION,
    CAST
}

public class SpellEvent {
    private final int id;
    private final EventType eventType;
    private final String action;

    public SpellEvent(int id, EventType eventType, String action){
        this.id = id;
        validateNullString(action);
        validateEmptyString(action);
        this.eventType = eventType;
        this.action = action;
    }

    private void validateNullString(String string){
        if (string == null)
            throw new NullPointerException("The string shouldn't be not null");
    }

    private void validateEmptyString(String string){
        if(string.isBlank())
            throw new IllegalArgumentException("The string shouldn't be empty");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if(obj == null || this.getClass() != obj.getClass())
            return false;

        SpellEvent spellEvent = (SpellEvent) obj;

        return this.id == spellEvent.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public int getId(){
        return id;
    }

    public EventType getType(){
        return eventType;
    }
}
