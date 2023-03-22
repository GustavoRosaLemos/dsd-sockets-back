package dsd.sockets.trabalho.controller;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import dsd.sockets.trabalho.model.Customer;
import dsd.sockets.trabalho.model.People;
import dsd.sockets.trabalho.model.SocketResponse;
import dsd.sockets.trabalho.repository.CustomerRepository;
import dsd.sockets.trabalho.repository.PeopleRepository;
import dsd.sockets.trabalho.service.PeopleService;
import dsd.sockets.trabalho.service.SocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class PeopleController {
    @Autowired
    PeopleService peopleService;

    public PeopleController() {
        SocketIOServer socketIOServer = SocketService.getInstance().getServer();
        socketIOServer.addEventListener("list_people", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String object, final AckRequest ackRequest) throws Exception {
                ackRequest.sendAckData(peopleService.findAllPeople());
            }
        });
        socketIOServer.addEventListener("get_people", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String cpf, final AckRequest ackRequest) throws Exception {
                ackRequest.sendAckData(peopleService.findPeople(cpf));
            }
        });
        socketIOServer.addEventListener("insert_people", People.class, new DataListener<People>() {
            @Override
            public void onData(SocketIOClient socketIOClient, People people, final AckRequest ackRequest) throws Exception {
                ackRequest.sendAckData(peopleService.savePeople(people));
            }
        });
        socketIOServer.addEventListener("delete_people", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String cpf, final AckRequest ackRequest) throws Exception {
                ackRequest.sendAckData(peopleService.deletePeople(cpf));
            }
        });
    }
}
