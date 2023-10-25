

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpServer {
    private static ServerSocket serverSocket;
    private static Socket clientSocket;

    public static void main(String[] args){

        //Recitation 10
        int portNumber = Integer.MAX_VALUE;
        if(args.length == 1){
            portNumber = Integer.parseInt(args[0]);
        }else {
            System.err.println("Incorrect Port Number Provided");
        }

        try {
            serverSocket = new ServerSocket(portNumber);
            System.out.println("Waiting for client request");

            clientSocket = serverSocket.accept();
            System.out.println("Successfully connected to the client");
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
