package dsd.sockets.trabalho.controller;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import dsd.sockets.trabalho.model.Employee;
import dsd.sockets.trabalho.model.People;
import dsd.sockets.trabalho.model.dto.EmployeeDTO;
import dsd.sockets.trabalho.service.EmployeeService;
import dsd.sockets.trabalho.service.SocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    public EmployeeController() {
        SocketIOServer socketIOServer = SocketService.getInstance().getServer();

        socketIOServer.addEventListener("list_employee", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String object, final AckRequest ackRequest) throws Exception {
                ackRequest.sendAckData(employeeService.findAllEmployee());
            }
        });
        socketIOServer.addEventListener("get_employee", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String cpf, final AckRequest ackRequest) throws Exception {
                ackRequest.sendAckData(employeeService.findEmployee(cpf));
            }
        });
        socketIOServer.addEventListener("insert_employee", Employee.class, new DataListener<Employee>() {
            @Override
            public void onData(SocketIOClient socketIOClient, Employee employee, final AckRequest ackRequest) throws Exception {
                ackRequest.sendAckData(employeeService.saveEmployee(employee));
            }
        });
        socketIOServer.addEventListener("delete_employee", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String cpf, final AckRequest ackRequest) throws Exception {
                ackRequest.sendAckData(employeeService.deleteEmployee(cpf));
            }
        });
        socketIOServer.addEventListener("update_employee", EmployeeDTO.class, new DataListener<EmployeeDTO>() {
            @Override
            public void onData(SocketIOClient socketIOClient, EmployeeDTO people, final AckRequest ackRequest) throws Exception {
                ackRequest.sendAckData(employeeService.updateEmployee(people));
            }
        });
    }
}
