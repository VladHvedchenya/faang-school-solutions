package LambdaExpressions.Task7;

public class Application {
    void main(){
        Droid white1 = new Droid("R2D2");
        Droid white2 = new Droid("BB-9");
        Droid white3 = new Droid("Main-10");

        white2.sendMessage(white1, "Where are you?", 7);
        white1.sendMessage(white2, "I'm in the Dark Prison! Save me, please!", 3);
        white2.sendMessage(white3, "I found him. He is in the Dark Prison. Let's save him!", 8);
        white3.sendMessage(white2, "On my way!", 12);
    }
}
