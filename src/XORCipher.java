import com.sun.tools.hat.internal.parser.HprofReader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class XORCipher {

    private String circularKey;
    private String originalText;
    private String cipheredText;
    private String binVal;

    public XORCipher(String originalText, String key) {
        key = key.toUpperCase();
        this.originalText = originalText.toLowerCase();
        int tempLen;
        this.circularKey = new String(new char[(tempLen = originalText.length()) / key.length() + 1]).replace("\0", key).substring(0, tempLen);
        this.binVal = calcBinVal();
        this.cipheredText = cipher();
    }

    private String cipher() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < binVal.length(); i += 8) {
            // It looks long, but essentially just do the conversion, and the new Character(...) stuff just converts
            //  the thing into Character so that it can be converted to string.
            result.append((new Character((char) ((int) Integer.valueOf(binVal.substring(i, i + 8), 2)))).toString());
        }
        return result.toString();
    }

    public void guess() {
        // A substitution for Scanner class, since it's a lot faster! (Please forgive my disobedience!!)
        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
        String guessedText = "";
        while (true) {
            System.out.printf("Key: %s, XOR Ciphered String: %s\n>", circularKey, cipheredText);
            try {
                guessedText = inp.readLine();
            } catch (Exception ignored) {
                continue;
            }
            if (guessedText.trim().equalsIgnoreCase(originalText)) {
                System.out.println("YOU ARE GENIUS!");
                break;
            } else {
                System.out.println("Nop!");
            }
        }
    }

    public String calcBinVal() {
        StringBuilder result = new StringBuilder();
        byte[] originalTextBytes = originalText.getBytes(StandardCharsets.UTF_8);
        byte[] circularKeyBytes = circularKey.getBytes(StandardCharsets.UTF_8);

        for (int i = 0; i < originalText.length(); i++) {
            int originalTextByte = originalTextBytes[i];
            int circularKeyByte = circularKeyBytes[i];
            for (int j = 0; j < 8; j++) {
                final int OTBin = (originalTextByte & 128) == 0 ? 0 : 1;
                final int CKBin = (circularKeyByte & 128) == 0 ? 0 : 1;
                originalTextByte <<= 1;
                circularKeyByte <<= 1;
                result.append((OTBin == CKBin) ? 0 : 1);
            }
        }

        return result.toString();
    }

    public String getBinVal() {
        return binVal;
    }

    private void setBinVal(String binVal) {
    }

    public String getKey() {
        return circularKey;
    }

    public void setKey(String key) {
        int tempLen;
        key = key.toUpperCase();
        this.circularKey = new String(new char[(tempLen = originalText.length()) / key.length() + 1]).replace("\0", key).substring(0, tempLen);
        this.cipheredText = cipher();
        this.binVal = calcBinVal();
    }

    public String getOriginalText() {
        return originalText;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText.toLowerCase();
        this.cipheredText = cipher();
        this.binVal = calcBinVal();
    }

    public String getCipheredText() {
        return cipheredText;
    }

    private void setCipheredText(String cipheredText) {}

    public String getCircularKey() {
        return circularKey;
    }

    private void setCircularKey(String circularKey) {}
}
