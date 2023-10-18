

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TcpServer {
    public static void main(String[] args){

        try{
            System.out.printf("Ip address:%s\n", InetAddress.getLocalHost());
        } catch (UnknownHostException e){
            e.printStackTrace(); //What would be the case this doesn't work

        } catch (Exception e) {
            e.printStackTrace();
        }

        
    }
}
