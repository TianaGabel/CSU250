import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;

public class TCPClient {
    private static Socket socket;
    private static DataInputStream inputData;

    public static void main(String[] args) throws IOException {

        String address = null;
        int portNumber = Integer.MAX_VALUE;
        if (args.length == 2) {
            address = args[0];
            portNumber = Integer.parseInt(args[1]);
        } else {
            System.out.println("Incorrect number of arguments provided");
            System.exit(0);
        }

        try {
            socket = new Socket(address, portNumber);

            inputData = new DataInputStream(socket.getInputStream());

            // Recieve messages
            int numMessages = inputData.readInt();
            int seedNumber = inputData.readInt();

            // initialize random number generator
            Random r = new Random(seedNumber);
            System.out.println("Received config");
            System.out.println("number of messages = " + numMessages);
            System.out.println("seed = " + seedNumber);

            long senderSum = 0;
            long receiverSum = 0;
            int currNum;
            int receivedNum;
            int numOfSentMessages = 0;
            int numOfReceivedMessages = 0;
            DataOutputStream outputData = new DataOutputStream(socket.getOutputStream());
            System.out.println("Starting to send messages to server...");
            for (int i = 0; i < numMessages; i++) {
                // Sends message
                currNum = r.nextInt();
                outputData.writeInt(currNum);
                outputData.flush();
                senderSum += currNum;
                numOfSentMessages++;
            }
            System.out.println("Finished sending messages to server.");
            System.out.println("Total messages sent: " + numOfSentMessages);
            System.out.println("Sum of messages sent: " + senderSum);

            System.out.println("Starting to listen for messages from server...");
            // TODO see if this counts as hard coding
            for (int i = 0; i < numMessages; i++) {
                receivedNum = inputData.readInt();
                receiverSum += receivedNum;
                numOfReceivedMessages++;
            }
            System.out.println("Finished listening for messages from server.");
            System.out.println("Total messages received: " + numOfReceivedMessages);
            System.out.println("Sum of messages received: " + receiverSum);

        } catch (EOFException eof) {
            // This is case submission 2 server is used
            inputData.close();
        } catch (IOException e) {
            System.err.println("Fatal connection Error");
            e.printStackTrace();
            System.exit(1);
        }

    }
}
