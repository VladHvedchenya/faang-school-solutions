package LambdaExpressions.Task7;

@FunctionalInterface
public interface DroidMessageEncryptor {
    String encrypt(String message, int encryptKey);
}
