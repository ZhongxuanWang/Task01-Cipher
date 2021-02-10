import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ROTCipher {
    private final char[] table =
            {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

    private int key;
    private String cipheredText;
    private String originalText;

    public ROTCipher(int key, String originalText) {
        this.key = key % 26;
        this.originalText = originalText.toUpperCase();
        this.cipheredText = cipher(this.originalText);
    }

    private String cipher(String originalText) {
        char[] arr = originalText.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();

        for (char c : arr) {
            int index = c - 'A' + key;
            if (index >= 26) {
                // Work with constraints
                index = index - 26;
            }
            stringBuilder.append( table[index] );
        }

        return stringBuilder.toString();
    }

    public void guess() {
        // A substitution for Scanner class, since it's a lot faster! (Please forgive my disobedience!!)
        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
        String guessedText = "";
        while (true) {
            System.out.printf("Key: %d, ROT Encrypted String: %s \n>", key, cipheredText);
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

    public int getKey() {
        return key;
    }

    public String getOriginalText() {
        return originalText;
    }

    public String getCipheredText() {
        return cipheredText;
    }

    public synchronized void setKey(int newKey) {
        this.key = newKey;
        this.cipheredText = cipher(originalText);
    }

    public void setOriginalText(String newText) {
        this.originalText = newText;
        this.cipheredText = cipher(this.originalText);
    }

    private void setCipheredText(String newCipheredText) {}
}
