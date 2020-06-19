package com.laven.v2;

import com.laven.v2.ProcessorHandler;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class SocketServerInitial implements ApplicationListener<ContextRefreshedEvent> {

    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ServerSocket serverSocket = null;

        while(true) {
            try {
                serverSocket = new ServerSocket(8889);
                Socket socket = serverSocket.accept();
                executorService.execute(new ProcessorHandler(socket));

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (serverSocket != null) {
                    try {
                        serverSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
