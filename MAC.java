import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;

public class MAC {
    public String msg;
    public String encryptedMessage;
    public boolean keySet = true;
    public Key key;

    public void main(String args[]) throws Exception{


        //Creating a KeyGenerator object
        KeyGenerator keyGen = KeyGenerator.getInstance("DES");

        //Creating a SecureRandom object
        SecureRandom secRandom = new SecureRandom();

        //Initializing the KeyGenerator
        keyGen.init(secRandom);

        //Creating/Generating a key

        key = keyGen.generateKey();

        //Creating a Mac object
        Mac mac = Mac.getInstance("HmacSHA256");

        //Initializing the Mac object
        mac.init(key);

        //Computing the Mac

        byte[] bytes = msg.getBytes();
        byte[] macResult = mac.doFinal(bytes);


        this.encryptedMessage = String.valueOf(macResult);
    }

    public void mainCaller() throws Exception {
        main(null);
    }


    public void setKey(Key keysetter)
    {
        this.keySet = true;
        this.key = keysetter;
    }
    public Key getKey()
    {
        return key;
    }
    public void setMsg(String message)
    {
        this.msg = message;
    }
    public String getEncryptedMessage()
    {
        return encryptedMessage;
    }
}