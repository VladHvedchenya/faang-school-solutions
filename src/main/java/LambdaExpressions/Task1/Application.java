package LambdaExpressions.Task1;

public class Application {
    void main(){
        SpellCaster caster = new SpellCaster();
        caster.cast("Прыжок", (spell) -> "Дверь открыта с помощью " + spell);
    }
}
