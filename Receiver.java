import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Receiver {

    RSA RSA = new RSA();

    AES AES = new AES();

    public String getEncryptedKey()
    {
        return RSA.getEncryptedMessage();
    }

    public void mainCaller() throws Exception {
        RSA.mainCaller();
    }

    //this functon handles the transmitted data file and utilizes whats given
    public void receiveMessage() throws FileNotFoundException {
        File file2 = new File("transmitted_Data.txt");
        Scanner scanner = new Scanner(file2);

        String receivedRSA = null;
        String receivedAES = null;
        String receivedMAC = null;

        while(scanner.hasNextLine())
        {
            if(scanner.nextLine().contains("RSA:"));
            {
                receivedRSA = scanner.nextLine();
                System.out.println("Received RSA: " + receivedRSA);
            }
            if(scanner.nextLine().contains("AES:"));
            {
                receivedAES = scanner.nextLine();
                System.out.println("Received AES: " + receivedAES);
            }
            if(scanner.nextLine().contains("MAC:"));
            {
                receivedMAC = scanner.nextLine();
                System.out.println("Received MAC: " + receivedMAC);
            }
        }
    }

    //function to get the decrypted AES Key using rsa private key
    public String getDecryptRSA()
    {
        return RSA.getDecryptedMessage();
    }

    public String AESDecrypt(String key)
    {
       // AES.setKey(key);

        AES.receiverCall(key);
        return AES.getDecryptedMessage();
    }

}
