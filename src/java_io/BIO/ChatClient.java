package java_io.BIO;

import java.io.*;
import java.net.Socket;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/5/13 9:10
 */
public class ChatClient {

    private static final String HOST = "127.0.0.1";

    private static final int PORT = 8087;

    private static final String QUIT = "quit";

    private Socket socket;

    private String host;

    private BufferedWriter writer;

    private BufferedReader reader;

    private int port;


    public ChatClient(String host, int port){
        this.host = host;
        this.port = port;
    }

    public ChatClient(){
        this(HOST,PORT);
    }

    public void start(){
        try {
            socket = new Socket(host,port);

            writer = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())
            );

            reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );


            new Thread(new UserInputHandler(this)).start();

            String msg;
            while((msg = receive())!=null){
                System.out.println(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            close();
        }
    }

    public void send(String msg) throws IOException {
    }

    private void close() {
        if(socket != null){
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String receive() throws IOException {
        String msg = null;
        if(!socket.isOutputShutdown()){
            msg = reader.readLine();
        }
        return msg;
    }

    public boolean readyToQuit(String input) {
        return QUIT.equals(input);
    }
}
