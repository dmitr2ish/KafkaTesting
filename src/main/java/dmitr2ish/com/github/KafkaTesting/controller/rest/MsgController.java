package dmitr2ish.com.github.KafkaTesting.controller.rest;

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

    final private KafkaTemplate<Long, String> kafkaTemplate;

    @Autowired
    public MsgController(KafkaTemplate<Long, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    //this is the producer, he send message with "msg" topic, if this topic wasn't, it will be create automatically
    @PostMapping
    public void sendOrder(Long msgId, String msg) {
        //it need for view the result of sending a message
        ListenableFuture<SendResult<Long, String>> future = kafkaTemplate.send("msg", msgId, msg);
        //addCalback have two parameters - SuccesCallback nad FailureCallback
        future.addCallback(System.out::println, System.err::println);
        kafkaTemplate.flush();
    }
}
