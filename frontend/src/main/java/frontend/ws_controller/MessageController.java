package frontend.ws_controller;
import frontend.dto.ChatMessage;
import frontend.service.ApiGatewayRequestUri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @Autowired ApiGatewayRequestUri apiGatewayRequestUri;
    @Autowired SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage){
        ChatMessage message = apiGatewayRequestUri.saveMessage(chatMessage).getBody();
        simpMessagingTemplate.convertAndSendToUser(chatMessage.getEmail(),"/queue/messages",chatMessage);
    }

}
