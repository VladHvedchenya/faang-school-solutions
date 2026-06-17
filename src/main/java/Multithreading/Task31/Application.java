package Multithreading.Task31;

import java.util.List;

public class Application {
    public static void main(String[] args){
        SuperHero thor = new SuperHero("Тор", 95, 70);
        SuperHero hulk = new SuperHero("Халк", 98, 40);
        SuperHero ironMan = new SuperHero("Железный Человек", 75, 85);
        SuperHero spiderMan = new SuperHero("Человек-Паук", 60, 95);
        SuperHero cap = new SuperHero("Капитан Америка", 75, 75);
        SuperHero panther = new SuperHero("Черная Пантера", 70, 90);
        SuperHero strange = new SuperHero("Доктор Стрэндж", 80, 80);
        SuperHero wolverine = new SuperHero("Росомаха", 80, 75);

        List<SuperHero> marvelRoster = List.of(thor, hulk, ironMan, spiderMan, cap, panther, strange, wolverine);

        SuperHeroTournament tournament = new SuperHeroTournament(marvelRoster);
        tournament.runCompetition();
    }
}