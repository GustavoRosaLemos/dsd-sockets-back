package dsd.sockets.trabalho.controller;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import dsd.sockets.trabalho.model.Order;
import dsd.sockets.trabalho.model.dto.OrderConfirmationDTO;
import dsd.sockets.trabalho.service.OrderService;
import dsd.sockets.trabalho.service.SocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class OrderController {
    @Autowired
    OrderService orderService;

    public OrderController() {
        SocketIOServer socketIOServer = SocketService.getInstance().getServer();

        socketIOServer.addEventListener("list_order", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String object, final AckRequest ackRequest) throws Exception {
                ackRequest.sendAckData(orderService.findAllOrders());
            }
        });
        socketIOServer.addEventListener("get_order", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String code, final AckRequest ackRequest) throws Exception {
                ackRequest.sendAckData(orderService.findOrder(code));
            }
        });
        socketIOServer.addEventListener("insert_order", Order.class, new DataListener<Order>() {
            @Override
            public void onData(SocketIOClient socketIOClient, Order people, final AckRequest ackRequest) throws Exception {
                ackRequest.sendAckData(orderService.saveOrder(people));
            }
        });
        socketIOServer.addEventListener("delete_order", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String code, final AckRequest ackRequest) throws Exception {
                ackRequest.sendAckData(orderService.deleteOrder(code));
            }
        });
        socketIOServer.addEventListener("confirm_order", OrderConfirmationDTO.class, new DataListener<OrderConfirmationDTO>() {
            @Override
            public void onData(SocketIOClient socketIOClient, OrderConfirmationDTO orderConfirmationDTO, final AckRequest ackRequest) throws Exception {
                ackRequest.sendAckData(orderService.confirmOrder(orderConfirmationDTO.getCode(), orderConfirmationDTO.getPaymentMethod()));
            }
        });

        socketIOServer.addEventListener("cancel_order", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String code, final AckRequest ackRequest) throws Exception {
                ackRequest.sendAckData(orderService.cancelOrder(code));
            }
        });
    }
}
