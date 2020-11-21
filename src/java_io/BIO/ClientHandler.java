package java_io.BIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/5/12 19:58
 */
public class ClientHandler implements Runnable {

    private ChatServer chatServer;

    private Socket socket;

    public ClientHandler(ChatServer chatServer, Socket socket) {
        this.chatServer = chatServer;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            chatServer.addClient(socket);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            String msg;
            while((msg = reader.readLine())!= null){
                chatServer.forwardMessage(socket, msg);
            }

            if(chatServer.readyToQuit(msg)){
                chatServer.removeClient(socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
