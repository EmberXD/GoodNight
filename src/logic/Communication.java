package logic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Communication {
    private static final String IP_ADDR = "139.199.12.213";
    private static final int PORT = 12345;

    public static String sendMessage(String args) {
        String ret;
        Socket socket = null;
        try {
            socket = new Socket(IP_ADDR, PORT);
            socket.setSoTimeout(5000);
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            outputStream.writeUTF(args);
            ret = inputStream.readUTF();
            outputStream.close();
            inputStream.close();
        } catch (Exception e) {
            return e.getMessage();
        }finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    return e.getMessage();
                }
            }
        }
        return ret;
    }
}
