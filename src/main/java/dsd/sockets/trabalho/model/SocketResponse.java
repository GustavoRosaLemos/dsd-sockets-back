package dsd.sockets.trabalho.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SocketResponse {
    private Boolean error;
    private String message;
    private Object content;
}
