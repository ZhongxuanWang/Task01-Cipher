import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class ROTCipher {
    private final char[] table =
            {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

    private final int key;
    private final String originalText;
    private final String decipherText;

    public ROTCipher(int key, String originalText) {
        this.key = key % 26;
        this.originalText = originalText.toUpperCase();
        this.decipherText = decipher(this.originalText);
    }

    private String decipher(String originalText) {
        char[] arr = originalText.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();

        for (char c : arr) {
            int index = c - 'A' + key;
            if (index >= 26) {
                // Work with constraints
                index = 26 - index;
            }
            stringBuilder.append( table[index] );
        }

        return stringBuilder.toString();
    }

    public void guess() {
        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
        String guessedText = "";
        try {
             guessedText = inp.readLine();
        } catch (Exception ignored) {}
        if (guessedText.trim().toUpperCase().equals(decipherText)) {
            System.out.println("Nop!");
        } else {
            System.out.println("YOU ARE GENIUS!");
        }
    }

    public int getKey() {
        return key;
    }

    public String getOriginalText() {
        return originalText;
    }

    public String getDecipherText() {
        return decipherText;
    }
}
