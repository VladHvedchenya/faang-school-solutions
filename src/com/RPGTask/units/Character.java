package com.RPGTask.units;

public abstract class Character {
    private final String name;
    private final int strength;
    private final int agility;
    private final int intelligence;
    private int health = 100;

    public Character(String name){
        if (name == null)
            throw new NullPointerException("The name can't be null!");
        if (name.isBlank())
            throw new IllegalArgumentException("The name can't be empty");
        this.name = name;
        this.strength = 5;
        this.agility = 5;
        this.intelligence = 5;
    }

    public Character(String name, int strength, int agility, int intelligence){
        if (name == null)
            throw new NullPointerException("The name can't be null!");
        if (name.isBlank())
            throw new IllegalArgumentException("The name can't be empty");
        if (strength < 0 || agility < 0 || intelligence < 0)
            throw new IllegalArgumentException("Creation failed. The parameters can't be < 0");
        this.name = name;
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
    }

    public int getStrength(){
        return strength;
    }

    public int getAgility(){
        return agility;
    }

    public abstract void attack(Character enemy);

    public boolean isDead(){
        return health == 0;
    }
    protected void applyDamage(int damage){
        health = Math.max(0, health - damage);
    }
}