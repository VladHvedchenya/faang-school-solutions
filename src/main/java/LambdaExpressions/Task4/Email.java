package LambdaExpressions.Task4;

public class Email {
    private final String subject;
    private String body;
    private final boolean isImportant;

    public Email(String subject, String body, boolean isImportant){
        //null-checks & empty-checks
        this.subject = subject;
        this.body = body;
        this.isImportant = isImportant;
    }

    public String getSubject(){
        return subject;
    }

    public void setBody(String string){
        this.body = string;
    }

    public String getBody(){
        return body;
    }

    public boolean isImportant(){
        return isImportant;
    }
}
