

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class TCPServer {
    private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static DataOutputStream DataOutputToClient;

    public static void main(String[] args){

        //check arguements
        int portNumber = Integer.MAX_VALUE;
        int seedNumber = 0;
        int numMessages = 0;
        if(args.length == 3){
            portNumber = Integer.parseInt(args[0]);
            if (portNumber <= 1024 || portNumber > 65535){
                System.out.println("Port number number must be between 1025 and 65535");
                System.exit(0);
            }
            seedNumber = Integer.parseInt(args[1]);
            numMessages = Integer.parseInt(args[2]);
        }else {
            System.err.println("Incorrect number of arguments provided");
            System.exit(0);
        }

        //Generate random number
        int randomNumber = generateRandomNumber(seedNumber);

        try {
            serverSocket = new ServerSocket(portNumber);
            System.out.println("Waiting for client request");

            clientSocket = serverSocket.accept();
            System.out.println("Successfully connected to the client");


            //TODO server sends 2 number to the client
            //TODO number of messages expected 
            //TODO first random number set up

            DataOutputToClient = new DataOutputStream(clientSocket.getOutputStream());
            //number of expected messages
            DataOutputToClient.writeInt(numMessages);
            //first random number generated with seed
            DataOutputToClient.writeInt(randomNumber);
            DataOutputToClient.flush();
            System.out.println("Successfully send information to client");

        } catch(IOException e){
            System.out.println("Could not listen on port " + portNumber);
            e.getMessage();
        } catch(Exception e){
            e.getMessage();
        }


        //This is from recitation 9
        try{
            System.out.printf("Ip address:%s\n", InetAddress.getLocalHost());
        } catch (UnknownHostException e){
            e.printStackTrace(); //What would be the case this doesn't work

        } catch (Exception e) {
            e.printStackTrace();
        }

        
    }

    private static int generateRandomNumber(long seed) {
        Random rand =  new Random(seed);
        return rand.nextInt();
    }
}
