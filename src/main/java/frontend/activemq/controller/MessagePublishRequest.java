/*
package frontend.activemq.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import frontend.api.request.CreateUserRequestDto;
import frontend.api.response.CreateUserResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessagePublishRequest {

    @Autowired
    private JmsTemplate jmsTemplate;
    private ObjectMapper mapper = new ObjectMapper();

    public <T> void listner(T object) {
        jmsTemplate.setDeliveryPersistent(true);
        jmsTemplate.receiveAndConvert();
        jmsTemplate.convertAndSend("frontend-microservice", object);
    }

    public void publishMessageForUserRegister(CreateUserRequestDto createUserRequestDto) {
    CreateUserResponseStatus createUserResponseStatus = null;
               jmsTemplate.convertAndSend("register-user", createUserRequestDto);
    }
}
*/
