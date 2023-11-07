import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;

public class TCPClient {
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
            System.out.println("Received config");
            System.out.println("number of messages = " + numMessages);
            System.out.println("seed = " + seedNumber);

            /* 
            long senderSum = 0;
            long receiverSum = 0;
            int currNum;
            int receivedNum;
            int numOfSentMessages = 0;
            int numOfReceivedMessages = 0;
            DataOutputStream outputData = new DataOutputStream(socket.getOutputStream());
            for(int i = 0; i < numMessages;i++){
                //Sends message
                currNum = r.nextInt();
                outputData.writeInt(currNum);
                outputData.flush();
                senderSum += currNum;
                numOfSentMessages ++;

                //TODO this does not work at this moment
                //receives message
                receivedNum = inputData.readInt();
                System.out.println(receivedNum);
                receiverSum += receivedNum;
                numOfReceivedMessages++;
            }

            */

        } catch(IOException e){
            System.err.println("Fatal connection Error");
            e.printStackTrace();
            System.exit(1);
        }

    }
}
