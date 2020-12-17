import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;



public class RSA {

    public String cipherText;
    public String decipheredMessage;
    public static KeyPair pair;
    //this method will return a pair of linked keys that can be used for RSA encyrption
    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048, new SecureRandom());
        pair = generator.generateKeyPair();

        return pair;
    }

    //this method can be used to encrypt a plaintext using the create RSA public key
    public static String encrypt(String plainText, PublicKey publicKey) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] cipherText = encryptCipher.doFinal(plainText.getBytes(UTF_8));

        return Base64.getEncoder().encodeToString(cipherText);
    }

    public static String decrypt(String cipherText, PrivateKey privateKey) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(cipherText);

        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);

        return new String(decryptCipher.doFinal(bytes), UTF_8);
    }

    public String getEncryptedMessage()
    {
        return cipherText;
    }

    public void mainCaller() throws Exception {
        main(null);
    }

    public String getDecryptedMessage()
    {
        return decipheredMessage;
    }
    public PrivateKey getPrivateKey()
    {
        return pair.getPrivate();
    }
    public void main(String[] args) throws Exception {

        //First generate a public/private key pair
        KeyPair pair = generateKeyPair();

        AES message = new AES();

        // System.out.println(message.getKey());
        String encryptMe = message.getKey();

        //Encrypt the message
        this.cipherText = encrypt(encryptMe, pair.getPublic());
        //System.out.println(cipherText);
        //Now decrypt it
        this.decipheredMessage = decrypt(cipherText, pair.getPrivate());

        // System.out.println(decipheredMessage);

    }
}