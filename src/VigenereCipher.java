import java.io.BufferedReader;
import java.io.InputStreamReader;

public class VigenereCipher {
    private final char[][] table = new char[26][26];

    private String key;
    private String circularKey;
    private String originalText;
    private String cipheredText;



    public VigenereCipher(String originalText, String key) {
        this.originalText = originalText;
        // Generate a key at the same length with the original text
        int tempLen;
        this.circularKey = new String(new char[(tempLen = originalText.length()) / key.length() + 1]).replace("\0", key).substring(0, tempLen);
        char[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        for (int i = 0; i < 26; i ++) {
            System.arraycopy(alphabet, 0, table[i], 0, 26);
        }

        this.cipheredText = cipher();
    }

    public String cipher() {
        // TODO
        return "";
    }

    public void showVigenereTable() {

    }

    public void guess() {
        // A substitution for Scanner class, since it's a lot faster! (Please forgive my disobedience!!)
        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
        String guessedText = "";
        while (true) {
            System.out.printf("Key: %s, Vigenere Ciphered String: %s\n", key, cipheredText);
            showVigenereTable();
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
        return key;
    }

    public String getOriginalText() {
        return originalText;
    }

    public String getCipheredText() {
        return cipheredText;
    }

    public synchronized void setKey(String newKey) {
        this.key = newKey;
        this.cipheredText = cipher();
    }

    public void setOriginalText(String newText) {
        this.originalText = newText;
        this.cipheredText = cipher();
    }

    private void setCipheredText(String newCipheredText) {}

}
