package hogwartsTask;

public class Application {
    void main(){
        HogwartsSpell hs = new HogwartsSpell();

        hs.addSpellEvent(EventType.CAST, "Shooting");
        hs.addSpellEvent(EventType.CAST, "Lightning");
        hs.addSpellEvent(EventType.DEFENCE, "Shield");
        hs.addSpellEvent(EventType.DEFENCE, "ExtraShield");
        hs.addSpellEvent(EventType.TRANSFORMATION, "To OWL");
        hs.addSpellEvent(EventType.TRANSFORMATION, "To RABBIT");

        hs.printAllSpellEvent();

        System.out.println(hs.getSpellEventById(3).getType().toString());

        for (SpellEvent event : hs.getSpellEventsByType(EventType.TRANSFORMATION)){
            System.out.println(event.getType().toString());
        }
    }
}
