package dmitr2ish.com.github.KafkaTesting.controller.rest;

import dmitr2ish.com.github.KafkaTesting.dto.UserDto;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/msg")
public class MsgController {

    final private KafkaTemplate<UUID, String> kafkaTemplate;

    @Autowired
    public MsgController(KafkaTemplate<UUID, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    //this is the producer, he send message with "msg" topic, if this topic wasn't, it will be create automatically
    @PostMapping
    public void sendOrder(@RequestBody UserDto user) {
        UUID key = UUID.randomUUID();
        Map<UUID, String> msg = creatingXmlFormat(user, key);
        //it need for view the result of sending a message
        ListenableFuture<SendResult<UUID, String>> future = kafkaTemplate.send("testServer1", key, msg.get(key));
        //addCalback have two parameters - SuccesCallback nad FailureCallback
        future.addCallback(System.out::println, System.err::println);
        kafkaTemplate.flush();
    }

    private Map<UUID, String> creatingXmlFormat(UserDto user, UUID key) {
        Map<UUID, String> msg = new HashMap<>();
        String xmlMessage =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<client id=\"" + key + "\"\n" +
                        "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                        "xsi:noNamespaceSchemaLocation=\"clientPassport.xsd\">\n" +
                        "<name>" + user.getName() + "</name>\n" +
                        "<surname>" + user.getSurname() + "</surname>\n" +
                        "   <passport>\n" +
                        "      <series>" + user.getPassportSeries() + "</series>\n" +
                        "      <number>" + user.getPassportNumber() + "</number>\n" +
                        "      <whoIssued>" + user.getWhoIssuedPassport() + "</whoIssued>\n" +
                        "      <whenIssued>" + user.getWhenIssuedPassport() + "</whenIssued>\n" +
                        "      <codeDivision>" + user.getDivisionCode() + "</codeDivision>\n" +
                        "   </passport>\n" +
                        "</client>";
        msg.put(key, xmlMessage);
        return msg;
    }
}
