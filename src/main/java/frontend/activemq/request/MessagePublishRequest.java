package frontend.activemq.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class MessagePublishRequest implements Serializable {
    private String message;
    private String source;
    private String destination;
}
