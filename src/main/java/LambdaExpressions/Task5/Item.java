package LambdaExpressions.Task5;

public class Item {
    private final String name;
    private final int value;

    public Item(String name, int value){
        //String null & empty checks. int checks for < 0
        this.name = name;
        this.value = value;
    }

    public String getName(){
        return name;
    }

    public int getValue(){
        return value;
    }
}
