package java_io.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/5/12 19:45
 */
public class ChatServer {

    private static final int PORT = 8087;

    private static final String QUIT = "quit";

    private int port;

    private ServerSocketChannel serverSocketChannel;

    private Selector selector;

    public ChatServer(int port){
        this.port = port;
    }

    public ChatServer(){
        this(PORT);
    }

    public void start(){

        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(port));

            selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("启动服务器， 监听端口：" + port + "...");

            while(true){
                selector.select();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void close() {
        if(serverSocketChannel != null){
            try {
                serverSocketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public boolean readyToQuit(String msg) {
        return QUIT.equals(msg);
    }

}
