package de.reetmeyer.cloud;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

public class AutoUploadClient {

    // ExitCodes:
    // 8 = Port must be between 0 and 55555
    // 9 = Failed to Connect
    // 2 = Unhandled IOException

    // Code 1,2

    public AutoUploadClient(String serverip, int port) {
        Socket socket = null;
        try {
            socket = new Socket(serverip, port);
        } catch (IllegalArgumentException e) {
            System.err.println("Port must be between 0 and 55555 and can not be " + port);
            System.exit(8);
        } catch (ConnectException e) {
            System.err.print("Failed to Connect to Server on ");
            System.out.println(serverip + ":" + port);
            System.exit(9);
        } catch (IOException e) {
            System.err.println("Unhandled Exception Code 1");
            e.printStackTrace();
            System.exit(2);
        }
        DataOutputStream dos = null;
        DataInputStream  dis = null;
        try {
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.err.println("Unhandled Exception Code 2");
            e.printStackTrace();
            System.exit(2);
        }



    }

    public static void main (String[] args){
        AutoUploadClient auc = new AutoUploadClient("localhost", 33445);
    }

}
