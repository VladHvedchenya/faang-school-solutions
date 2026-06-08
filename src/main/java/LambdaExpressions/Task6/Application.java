package LambdaExpressions.Task6;

public class Application {
    void main(){
        RemoteService service = new RemoteService();
        String res = ErrorHandler.withErrorHandling(
                () -> service.call(""),
                e -> "Доступ к сервису запрещен"
        );

        System.out.println(res);
    }
}