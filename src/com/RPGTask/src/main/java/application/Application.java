package application;

import units.Archer;
import units.Warrior;

public class Application {
    void main(){
        Warrior warrior = new Warrior("Vlad");
        Archer archer = new Archer("Dima");

        while (!warrior.isDead() && !archer.isDead()){
            warrior.attack(archer);

            if(archer.isDead()){
                System.out.println("Warrior Won!");
                break;
            }

            archer.attack(warrior);

            if(warrior.isDead()){
                System.out.println("Archer Won!");
                break;
            }
        }
    }
}
