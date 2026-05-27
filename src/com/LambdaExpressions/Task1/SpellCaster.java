package com.LambdaExpressions.Task1;

public class SpellCaster {
    public void cast(String spellName, SpellAction action){
        System.out.println(action.act(spellName));
    }
}
