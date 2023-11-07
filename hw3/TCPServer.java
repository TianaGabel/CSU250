

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class TCPServer {
    private static ServerSocket serverSocket;
    private static ServerSocket serverSocket2;
    private static Socket clientSocket1;
    private static Socket clientSocket2;
    private static DataOutputStream DataOutputToClient;

    public static void main(String[] args){

        try{
            System.out.printf("IP Address: %s\n", InetAddress.getLocalHost());
        } catch (UnknownHostException e){
            e.printStackTrace(); //What would be the case this doesn't work

        } catch (Exception e) {
            e.printStackTrace();
        }

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
            System.out.println("Waiting for client");

            clientSocket1 = serverSocket.accept();
            clientSocket2 = serverSocket.accept();
            System.out.println("Successfully connected to the clients");

            
            System.out.println(clientSocket1.getInetAddress().getHostName() + " " + randomNumber1);
            System.out.println(clientSocket2.getInetAddress().getHostName() + " " + randomNumber2);

            //Send 2 numbers
            DataOutputToClient = new DataOutputStream(clientSocket1.getOutputStream());
            //number of expected messages
            DataOutputToClient.writeInt(numMessages);
            //first random number generated with seed
            DataOutputToClient.writeInt(randomNumber1);
            DataOutputToClient.flush();

            DataOutputToClient = new DataOutputStream(clientSocket2.getOutputStream());
            //number of expected messages
            DataOutputToClient.writeInt(numMessages);
            //first random number generated with seed
            DataOutputToClient.writeInt(randomNumber2);
            DataOutputToClient.flush();

            System.out.println("Successfully sent information to client");

            //Recieve numbers from client
            DataInputStream inputData = new DataInputStream(clientSocket1.getInputStream());
            for(int i = 0; i < numMessages; i++){
            }


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

}
