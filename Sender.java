import java.io.FileWriter;
import java.io.IOException;

public class Sender {


AES AES = new AES();

MAC MAC = new MAC();

//settign the message to be encrypted
public void setMessage(String message)
{
    AES.setMessage(message);
    AES.mainCaller();
}
//getting the encrypted message
public String getEncMessage()
{
    return AES.getEncryptedMessage();
}
//uses MAC function to MAC the provided message
public void macMe(String message)
{
    MAC.setMsg(message);
}
//calling main to produce MAC encrypt key
public void callMainMAC() throws Exception {
    MAC.mainCaller();
}
//getting MAC'ed message
public String getMACENCMessage()
{
    return MAC.getEncryptedMessage();
}

//function to write all the encrypted text to a file to send to the receiver
public void writeToFile(String RSA, String AES, String MAC) throws IOException {

    FileWriter myWriter = new FileWriter("transmitted_Data.txt");

    try {
        myWriter.write("RSA:\n");
        myWriter.write(RSA + "\n");
        myWriter.write("AES:\n");
        myWriter.write(AES + "\n");
        myWriter.write("MAC:\n");
        myWriter.write(MAC);
        myWriter.close();

    }
    catch (IOException e)
    {
        System.out.println("An Error Occured. ");
        e.printStackTrace();
    }
}


}
