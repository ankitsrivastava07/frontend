/*
package frontend.activemq.convertor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

@Component
public class CreateUserResponseStatusConverter implements MessageConverter {

  private static final Logger LOGGER =
          LoggerFactory.getLogger(CreateUserResponseStatusConverter.class);

  private ObjectMapper mapper;

  public CreateUserResponseStatusConverter() {
    mapper = new ObjectMapper();
  }

  @Override
  public Message toMessage(Object object, Session session)
          throws JMSException {
    CreateUserResponseStatusConverter createUserResponseStatusConverter = (CreateUserResponseStatusConverter) object;
    String payload = null;
    try {
      payload = mapper.writeValueAsString(createUserResponseStatusConverter);
      LOGGER.info("outbound json='{}'", payload);
    } catch (JsonProcessingException e) {
      LOGGER.error("error converting form person", e);
    }

    TextMessage message = session.createTextMessage();
    message.setText(payload);

    return message;
  }

  @Override
  public CreateUserResponseStatusConverter fromMessage(Message message) {
    CreateUserResponseStatusConverter createUserRequestDto = null;
    try {
    TextMessage textMessage = (TextMessage) message;
    String payload = textMessage.getText();
    LOGGER.info("inbound json='{}'", payload);
      createUserRequestDto = mapper.readValue(payload, CreateUserResponseStatusConverter.class);
      return createUserRequestDto;
    } catch (Exception e) {
      LOGGER.error("error converting to person", e);
      return createUserRequestDto;
    }
  }
}*/
