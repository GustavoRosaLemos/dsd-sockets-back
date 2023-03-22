package dsd.sockets.trabalho.controller;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import dsd.sockets.trabalho.model.Customer;
import dsd.sockets.trabalho.model.People;
import dsd.sockets.trabalho.service.CustomerService;
import dsd.sockets.trabalho.service.SocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    public CustomerController() {
        SocketIOServer socketIOServer = SocketService.getInstance().getServer();

        socketIOServer.addEventListener("list_customer", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String object, final AckRequest ackRequest) throws Exception {
                ackRequest.sendAckData(customerService.findAllCustomers());
            }
        });
        socketIOServer.addEventListener("insert_customer", Customer.class, new DataListener<Customer>() {
            @Override
            public void onData(SocketIOClient socketIOClient, Customer customer, final AckRequest ackRequest) throws Exception {
                ackRequest.sendAckData(customerService.saveCustomer(customer));
            }
        });
        socketIOServer.addEventListener("get_customer", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String cpf, final AckRequest ackRequest) throws Exception {
                ackRequest.sendAckData(customerService.findCustomer(cpf));
            }
        });
        socketIOServer.addEventListener("delete_customer", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String cpf, final AckRequest ackRequest) throws Exception {
                ackRequest.sendAckData(customerService.deleteCustomer(cpf));
            }
        });
        socketIOServer.addEventListener("update_customer", Customer.class, new DataListener<Customer>() {
            @Override
            public void onData(SocketIOClient socketIOClient, Customer customer, final AckRequest ackRequest) throws Exception {
                ackRequest.sendAckData(customerService.updateCustomer(customer));
            }
        });
    }
}
