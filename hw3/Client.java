import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;

public class Client {
    private static Socket socket;
    private static DataInputStream inputData;

    public static void main(String[] args){

        String address = null;
        int portNumber = Integer.MAX_VALUE;
        if (args.length == 2){
            address = args[0];
            portNumber = Integer.parseInt(args[1]);
        }else{
            System.out.println("Incorrect number of arguments provided");
            System.exit(0);
        }

        try{
            socket = new Socket(address,portNumber);

            inputData = new DataInputStream(socket.getInputStream());

            //Recieve messages
            int numMessages = inputData.readInt();
            int seedNumber = inputData.readInt();

            //initialize random number generator
            Random r = new Random(seedNumber);

            long senderSum = 0;
            int currNum;
            int numOfSentMessages = 0;
            for(int i = 0; i < numMessages;i++){
                currNum = r.nextInt();
                //send number to the server TODO
                senderSum += currNum;
                numOfSentMessages ++;
            }



        } catch(IOException e){
            System.err.println("Fatal connection Error");
            e.printStackTrace();
            System.exit(1);
        }

    }
}
