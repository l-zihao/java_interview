package java_io.BIO;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/5/12 19:45
 */
public class ChatServer {

    private static final int PORT = 8087;

    private static final String QUIT = "quit";

    private int port;

    private ServerSocket serverSocket;

    private Map<Integer, Writer> connectedClients;

    public ChatServer(int port){
        this.port = port;
        connectedClients = new HashMap<>();
    }

    public ChatServer(){
        this(PORT);
        connectedClients = new HashMap<>();
    }

    public void start(){

        try {
            serverSocket = new ServerSocket(port);

            while(true){
                Socket socket = serverSocket.accept();
                System.out.println("客户端[" + socket.getPort() + "]已连接客户端" );
                new Thread(new ClientHandler(this, socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            close();
        }

    }

    private void close() {
        if(serverSocket != null){
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addClient(Socket socket) throws IOException {
        int port = socket.getPort();
        if(!connectedClients.containsKey(port)){
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())
            );
            connectedClients.put(port, writer);
        }
    }

    public void forwardMessage(Socket socket, String msg) throws IOException {
        for (Integer port : connectedClients.keySet()) {
            if(port != socket.getPort()){
                BufferedWriter writer = (BufferedWriter) connectedClients.get(port);
                writer.write(msg);
            }
        }
    }

    public boolean readyToQuit(String msg) {
        return QUIT.equals(msg);
    }

    public void removeClient(Socket socket) throws IOException {
        if(socket != null){
            socket.getOutputStream().close();
            connectedClients.remove(socket.getPort());
        }
    }
}
