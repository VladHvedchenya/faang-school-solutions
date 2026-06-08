package Collections.CatchEvents.src.main.java.hogwartsTask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class HogwartsSpell {
    private Map<Integer, SpellEvent> spellById;
    private Map<EventType, List<SpellEvent>> spellsByType;
    private int currentSpellCount = 0;

    public HogwartsSpell(){
        spellById = new HashMap<>();
        spellsByType = new HashMap<>();
    }

    public void addSpellEvent(EventType type, String description){
        SpellEvent event = new SpellEvent(++currentSpellCount, type, description);
        spellById.put(event.getId(), event);
        spellsByType.putIfAbsent(type, new ArrayList<>());
        spellsByType.get(type).add(event);
    }

    public SpellEvent getSpellEventById(int id){
        return spellById.getOrDefault(id, null);
    }

    public List<SpellEvent> getSpellEventsByType(EventType type){
        return List.copyOf(spellsByType.getOrDefault(type, Collections.emptyList()));
    }

    public void deleteSpellEventById(int id){
        SpellEvent event = spellById.getOrDefault(id, null);

        if(event == null)
            throw new NoSuchElementException("Event not found");

        spellById.remove(id);
        spellsByType.get(event.getType()).remove(event);
    }

    public void printAllSpellEvent(){
        for(Map.Entry<Integer, SpellEvent> entry : spellById.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue().getType());
        }
    }
}
