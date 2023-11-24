import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LetterMeter extends Thread {
    static String phrase;

    LetterMeter(char letter) {
        this.setName("" + letter);
        this.start();
    }

    public static void main(String[] args) {
        char[] letters = {'a', 'e', 'i', 'o', 'u'};
        phrase = getUserInput();

        LetterMeter[] threads = new LetterMeter[letters.length];

        for (int i = 0; i < letters.length; i++) threads[i] = new LetterMeter(letters[i]);
    }

    private static String getUserInput() {
        BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Write a phrase: ");
        try {
            return userIn.readLine().toLowerCase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        int vowelQTY = 0;
        for (int i = 0; i < phrase.length(); i++) {
            if (phrase.charAt(i) == this.getName().charAt(0)) {
                vowelQTY++;
            }
        }
        System.out.println("Hilo(" + this.getName() + "): Vowel '" + this.getName() + "' quantity = " + vowelQTY);
    }
}
