package dsd.sockets.trabalho.config;


import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.ConnectListener;
import lombok.Getter;

public class SocketConfiguration {
    private static SocketConfiguration instance;
    @Getter
    private final Configuration configuration = new Configuration();

    private SocketConfiguration() {
        configuration.setHostname("localhost");
        configuration.setPort(9092);
    }

    public synchronized static SocketConfiguration getInstance() {
        if (instance == null) {
            instance = new SocketConfiguration();
        }
        return instance;
    }
}
