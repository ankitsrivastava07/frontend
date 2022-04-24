package frontend.dto;
import lombok.Data;

@Data
public class ChatMessage {
    private String id;
    private String senderId;
    private String senderName;
    private String receiverName;
    private String receiverId;
    private String content;
    private String email;
}
