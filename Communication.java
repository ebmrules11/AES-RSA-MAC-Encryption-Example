import java.io.*;
import java.security.Key;
import java.util.Scanner;
public class Communication {


    public static void main(String[] args) throws Exception {

        //reading in the original message from the file
        File file = new File("OriginalMessage");
        Scanner sc = new Scanner(file);

        String OriginalMessage = sc.nextLine();

        //creating an instance of both the receiver and sender
        Sender sender = new Sender();
        Receiver receiver = new Receiver();

        //setting the message to be sent
        sender.setMessage(OriginalMessage);

        //encrypted the message with AES
        String AESencrypytedMessage = sender.getEncMessage();

        System.out.println("AES Encrypted Message: " + AESencrypytedMessage);

        //calling main in receiver to encrypt using their public key
        receiver.mainCaller();

        String RSAencryptedMessage = receiver.getEncryptedKey();

        System.out.println("RSA Encrypted Message: " + RSAencryptedMessage);

        //MACing the message using the sender's MAC function
        sender.macMe(OriginalMessage);
        sender.callMainMAC();

        String MACEncryptedMessage = sender.getMACENCMessage();

        System.out.println("MAC Encrypted Message: " + MACEncryptedMessage);

        sender.writeToFile(RSAencryptedMessage, AESencrypytedMessage, MACEncryptedMessage);

        System.out.println("The file was created and sent to the receiver!");

        //calling the receivers get message function
        receiver.receiveMessage();

        System.out.println("Decrypting the RSA Ciphertext using private key...");

        System.out.println("Decrypted RSA key: " + receiver.getDecryptRSA());

        System.out.println("Using the decrypted key to decrypt the AES Cipher Text.....");

        //using the receivers AES Decrypt function to decrypt the AES Cipher text
        String receiverAESDecrypt = receiver.AESDecrypt(receiver.getDecryptRSA());

        System.out.println("Decrypted AES Ciphertext: " + receiverAESDecrypt);

        //setting the received message to the message into the MAC using the same MAC key as the sender
        sender.macMe(receiverAESDecrypt);

        String compareMAC = sender.getMACENCMessage();

        System.out.println("Comparing MAC Values, from file and from new MAC");
        System.out.println("OLD MAC: " + MACEncryptedMessage + " NEW MAC: " + compareMAC);

        //Comparing found MAC's
        if(MACEncryptedMessage.equals(compareMAC))
        {
            System.out.println("Message succesfully Authenticated!");
        }
        else
        {
            System.out.println("Message was intercepted!");
        }








    }

}
