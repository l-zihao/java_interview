package java_io.BIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/5/13 9:53
 */
public class UserInputHandler implements Runnable {

    private ChatClient chatClient;

    public UserInputHandler(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public void run() {
        BufferedReader consoleReader = new BufferedReader(
                new InputStreamReader(System.in)
        );
        String input = null;
        while(true){
            try {
                if (!((input=consoleReader.readLine())!=null)){
                    chatClient.send(input);

                    if(chatClient.readyToQuit(input)){
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
