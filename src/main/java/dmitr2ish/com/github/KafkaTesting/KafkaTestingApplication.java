package dmitr2ish.com.github.KafkaTesting;

import dmitr2ish.com.github.KafkaTesting.dto.UserDto;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@EnableKafka
@SpringBootApplication
public class KafkaTestingApplication {

	@KafkaListener(topics="msg")
	public void msgListener(ConsumerRecord<Long, UserDto> record) {
		System.out.println("-= LISTENER by msg topic IS ACTIVE =-");
		System.out.println("partition: " + record.partition());
		System.out.println("key: " + record.key());
		System.out.println("value" + record.value());
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaTestingApplication.class, args);
	}

}
