package dmitr2ish.com.github.KafkaTesting.controller.rest;

import dmitr2ish.com.github.KafkaTesting.dto.Information;
import dmitr2ish.com.github.KafkaTesting.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

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
    public void sendOrder(Long msgId, @RequestBody UserDto msg) {
        msg.setSomeinformation(new Information(randomString(msg.getAge()+msg.getName().length())));
        //it need for view the result of sending a message
        ListenableFuture<SendResult<Long, UserDto>> future = kafkaTemplate.send("msg", msgId, msg);
        //addCalback have two parameters - SuccesCallback nad FailureCallback
        future.addCallback(System.out::println, System.err::println);
        kafkaTemplate.flush();
    }

    //secondary method for create random string
    String randomString(int someInt) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < someInt; i++) {
            char c = (char)(r.nextInt(Character.MAX_VALUE));
            sb.append(c);
        }
        return sb.toString();
    }
}
