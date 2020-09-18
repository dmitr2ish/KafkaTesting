package dmitr2ish.com.github.KafkaTesting.controller.rest;

import dmitr2ish.com.github.KafkaTesting.dto.Address;
import dmitr2ish.com.github.KafkaTesting.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/msg")
public class MsgController {

    final private KafkaTemplate<Long, UserDto> kafkaTemplate;

    @Autowired
    public MsgController(KafkaTemplate<Long, UserDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    //this is the producer, he send message with "msg" topic, if this topic wasn't, it will be create automatically
    @PostMapping
    public void sendOrder(Long msgId, UserDto msg) {
        msg.setAddress(new Address("Rus", "Msk", "Lenina", 9L, 9L));
        //it need for view the result of sending a message
        ListenableFuture<SendResult<Long, UserDto>> future = kafkaTemplate.send("msg", msgId, msg);
        //addCalback have two parameters - SuccesCallback nad FailureCallback
        future.addCallback(System.out::println, System.err::println);
        kafkaTemplate.flush();
    }
}
