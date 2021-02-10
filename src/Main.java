/**
 * This project is open-sourced and is hosted on GitHub
 * Link: https://github.com/ZhongxuanWang/Task01-Cipher
 * @version 1.0
 * @author wangzhongxuan
 */

public class Main {
    public static void main(String[] args) {

        // A warm notice, inputs are NOT Case-Sensitive :)
        // Though you are recommended to treat it as case-sensitive because... Well, you can see the point don't you?

        ROTCipher rotCipher = new ROTCipher(13, "DanielIsSmart");
        rotCipher.guess();

        VigenereCipher vigenereCipher = new VigenereCipher("DanielIsSmart", "IKnewIt");
        vigenereCipher.guess();

        XORCipher xorCipher = new XORCipher("DanielIsSmart", "YouAreRight");
        xorCipher.guess();
    }
}
