

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPServer {
    private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static DataOutputStream DataOutputToClient;

    public static void main(String[] args){

        //Recitation 10
        int portNumber = Integer.MAX_VALUE;
        if(args.length == 3){
            portNumber = Integer.parseInt(args[0]);
            if (portNumber <= 1024 || portNumber > 65535){
                System.out.println("Port number number must be between 1025 and 65535");
                System.exit(0);
            }
        }else {
            System.err.println("Incorrect number of arguments provided");
        }

        //Check seed
        //Check num of messages

        try {
            serverSocket = new ServerSocket(portNumber);
            //TODO exception for in use port number should print exception.getMessage()
            System.out.println("Waiting for client request");


            //TODO Generate random number using seed

            clientSocket = serverSocket.accept();
            System.out.println("Successfully connected to the client");


            //TODO server sends 2 number to the client
            //TODO number of messages expected 
            //TODO first random number set up

            DataOutputToClient = new DataOutputStream(clientSocket.getOutputStream());
            DataOutputToClient.writeInt(100);
            DataOutputToClient.flush();
            System.out.println("Successfully send information to client");

        } catch(IOException e){
            System.out.println("Could not listen on port " + portNumber);
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
