

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;

public class TCPServer {
    private static ServerSocket serverSocket;
    private static Socket clientSocket1;
    private static Socket clientSocket2;
    private static DataOutputStream DataOutputToClient;
    private static DataInputStream DataInputToClient;
    private static int seedNumber = 0;
    private static int numMessages = 0;
    private static int portNumber = Integer.MAX_VALUE;
    //It is okay for these values to be static as there will only one server in our usage

    public static void main(String[] args){

        try{
            System.out.printf("IP Address: %s\n", InetAddress.getLocalHost());
        } catch (UnknownHostException e){
            e.printStackTrace(); //What would be the case this doesn't work

        } catch (Exception e) {
            e.printStackTrace();
        }

        //check arguements
        if(args.length == 3){
            portNumber = Integer.parseInt(args[0]);
            if (portNumber <= 1024 || portNumber > 65535){
                System.out.println("Port number number must be between 1025 and 65535");
                System.exit(0);
            }
            System.out.println("Port Number " + portNumber);
            seedNumber = Integer.parseInt(args[1]);
            numMessages = Integer.parseInt(args[2]);
        }else {
            System.err.println("Incorrect number of arguments provided");
            System.exit(0);
        }

        //Generate random number
        Random rand =  new Random(seedNumber);
        int randomNumber1 = rand.nextInt();
        int randomNumber2 = rand.nextInt();
        
        try {
            serverSocket = new ServerSocket(portNumber);
            System.out.println("waiting for client...");

            clientSocket1 = serverSocket.accept();
            clientSocket2 = serverSocket.accept();
            System.out.println("Clients Connected!");

            System.out.println("Sending config to clients...");
            sendConfigData(clientSocket1, randomNumber1);
            sendConfigData(clientSocket2, randomNumber2);

            System.out.println(clientSocket1.getInetAddress().getHostName() + " " + randomNumber1);
            System.out.println(clientSocket2.getInetAddress().getHostName() + " " + randomNumber2);
            System.out.println("Finished sending config to clients.");

            System.out.println("Starting to listen for client messages...");
            ArrayList<Integer> client1Messages = recieveMessages(clientSocket1);
            ArrayList<Integer> client2Messages = recieveMessages(clientSocket2);
            

            System.out.println("Finished Listening for client messages.");
            System.out.println(clientSocket1.getInetAddress().getHostName());
            System.out.println("\tMessages received: " + client1Messages.size());
            long sum1 = 0;
            for (int i: client1Messages) {sum1 += i;}
            System.out.println("\tSum received: " + sum1);

            System.out.println(clientSocket2.getInetAddress().getHostName());
            System.out.println("\tMessages received: " + client2Messages.size());
            long sum2 = 0;
            for (int i: client2Messages) {sum2 += i;}
            System.out.println("\tSum received: " + sum2);

            //Relaying messages to clients
            relayClientToClientMessage(clientSocket1,client2Messages);
            relayClientToClientMessage(clientSocket2,client1Messages);
        } catch(IOException e){
            System.out.println("Could not listen on port " + portNumber);
            e.getMessage();
        } catch(Exception e){
            e.getMessage();
        }
    }

    private static ArrayList<Integer> recieveMessages(Socket clientSocket) throws IOException {
        Integer receivedNum;
        ArrayList<Integer> receivedNumbers = new ArrayList<>();
        DataInputToClient = new DataInputStream(clientSocket.getInputStream());
        for (int i = 0; i < numMessages; i++) {
            receivedNum = DataInputToClient.readInt();
            receivedNumbers.add(receivedNum);
        }
        return receivedNumbers;
    }

    public static void relayClientToClientMessage(Socket clientSocket, ArrayList<Integer> messages) throws Exception{
        DataOutputToClient = new DataOutputStream(clientSocket.getOutputStream());
        for (int i: messages){
            DataOutputToClient.writeInt(i);
        }
        DataOutputToClient.flush();
    }
    
    public static void sendConfigData(Socket clientSocket, int rand) throws Exception{
        //Send 2 numbers
            DataOutputToClient = new DataOutputStream(clientSocket.getOutputStream());
            //number of expected messages
            DataOutputToClient.writeInt(numMessages);
            //first random number generated with seed
            DataOutputToClient.writeInt(rand);
            DataOutputToClient.flush();
    }
}
