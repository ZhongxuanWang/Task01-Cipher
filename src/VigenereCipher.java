import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;

public class VigenereCipher {
    private final char[][] table = new char[26][26];

    private String circularKey;
    private String originalText;
    private String cipheredText;

    public VigenereCipher(String originalText, String key) {
        this.originalText = originalText.toUpperCase().trim();
        key = key.toUpperCase();
        // Generate a key at the same length with the original text
        int tempLen;
        this.circularKey = new String(new char[(tempLen = originalText.length()) / key.length() + 1]).replace("\0", key).substring(0, tempLen);
        char[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        for (int i = 0; i < 26; i ++) {
            for (int j = 0; j < 26; j ++) {
                table[i][j] = alphabet[(i+j)%26];
            }
        }

        this.cipheredText = cipher();
    }

    public String cipher() {
        StringBuilder s = new StringBuilder();
        int i = 0;
        for (char c : originalText.toCharArray()) {
            s.append(table[circularKey.toCharArray()[i] - 'A'][c - 'A']);
            i++;
        }
        return s.toString();
    }

    public void showVigenereTable() {
        System.out.println(Arrays.deepToString(table).replace("], [", "], \n["));
    }

    public void guess() {
        // A substitution for Scanner class, since it's a lot faster! (Please forgive my disobedience!!)
        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
        String guessedText = "";
        while (true) {
            showVigenereTable();
            System.out.printf("Key: %s, Vigenere Ciphered String: %s\n>", circularKey, cipheredText);
            try {
                guessedText = inp.readLine();
            } catch (Exception ignored) {
                continue;
            }
            if (guessedText.trim().toUpperCase().equals(originalText)) {
                System.out.println("YOU ARE GENIUS!");
                break;
            } else {
                System.out.println("Nop!");
            }
        }
    }

    public String getKey() {
        return circularKey;
    }

    public String getOriginalText() {
        return originalText;
    }

    public String getCipheredText() {
        return cipheredText;
    }

    public synchronized void setKey(String newKey) {
        newKey = newKey.toUpperCase();
        int tempLen;
        this.circularKey = new String(new char[(tempLen = originalText.length()) / newKey.length() + 1]).replace("\0", newKey).substring(0, tempLen);
        this.cipheredText = cipher();
    }

    public void setOriginalText(String newText) {
        this.originalText = newText;
        this.cipheredText = cipher();
    }

    private void setCipheredText(String newCipheredText) {}

}
