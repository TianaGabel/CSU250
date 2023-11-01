import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

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
            //incorrect port number or ip provided
        }



        try{
            socket = new Socket(address,portNumber);
            //connected to server

            inputData = new DataInputStream(socket.getInputStream());
            System.out.println("Received info " + inputData.read());

        } catch(IOException e){
            System.err.println("Fatal connection Error");
            e.printStackTrace();
            System.exit(1);
        }

    }
}
