package frontend.activemq.listener;

import frontend.activemq.request.MessagePublishRequest;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/*
@Component
@EnableJms
public class MessageListner {
    @JmsListener(destination = "frontend-microservice")
    public void messageListener(MessagePublishRequest messagePublishRequest){
          messagePublishRequest.getMessage();
        System.out.println(messagePublishRequest.getMessage());
        System.out.println(messagePublishRequest.getSource());
    }

}
*/
