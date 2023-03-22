package dsd.sockets.trabalho.service;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import dsd.sockets.trabalho.config.SocketConfiguration;
import lombok.Getter;
import org.springframework.stereotype.Service;
public class SocketService {
    private static SocketService instance;
    @Getter
    private final SocketIOServer server;

    private SocketService() {
        server = new SocketIOServer(SocketConfiguration.getInstance().getConfiguration());
        server.start();
        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient socketIOClient) {
                System.out.println(socketIOClient.getSessionId() + " se conectou!");
            }
        });
    }

    public synchronized static SocketService getInstance() {
        if (instance == null) {
            instance = new SocketService();
            System.out.println("Nova instancia socket criada!");
        }
        return instance;
    }
}
