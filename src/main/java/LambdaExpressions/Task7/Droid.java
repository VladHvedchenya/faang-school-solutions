package LambdaExpressions.Task7;

public class Droid {
    private final String name;

    public Droid(String name){
        //String null & empty cheks
        this.name = name;
    }

    public void sendMessage(Droid droid, String message, int key){
        droid.receiveMessage(encrypt(message, key), key);
    }

    private void receiveMessage(String message, int key){
        System.out.println(this.name + " получил зашифрованное сообщение " + message);
        System.out.println(this.name + " расшифровал сообщение " + decrypt(message, key));
    }

    private String encrypt(String message, int key){
        DroidMessageEncryptor encryptor = (m, k) -> {
            StringBuilder sb = new StringBuilder();
            String alphabet = "abcdefghijklmnopqrstuvwxyz"; //26

            for (Character c : m.toCharArray()){
                if (Character.isLetter(c)){
                    int index = alphabet.indexOf(Character.toLowerCase(c));
                    int newIndex = (index - k + alphabet.length()) % alphabet.length();

                    if(Character.isUpperCase(c))
                        sb.append(Character.toUpperCase(alphabet.charAt(newIndex)));
                    else
                        sb.append(alphabet.charAt(newIndex));
                }
                else{
                    sb.append(c);
                }
            }

            return sb.toString();
        };

        return encryptor.encrypt(message, key);
    }

    private String decrypt(String message, int key){
        DroidMessageEncryptor encryptor = (m, k) -> {
            StringBuilder sb = new StringBuilder();
            String alphabet = "abcdefghijklmnopqrstuvwxyz"; //26

            for (Character c : m.toCharArray()){
                if (Character.isLetter(c)){
                    int index = alphabet.indexOf(Character.toLowerCase(c));
                    int newIndex = (index + k + alphabet.length()) % alphabet.length();

                    if(Character.isUpperCase(c))
                        sb.append(Character.toUpperCase(alphabet.charAt(newIndex)));
                    else
                        sb.append(alphabet.charAt(newIndex));
                }
                else{
                    sb.append(c);
                }
            }

            return sb.toString();
        };

        return encryptor.encrypt(message, key);
    }
}