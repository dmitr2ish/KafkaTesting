package dmitr2ish.com.github.KafkaTesting;

import dmitr2ish.com.github.KafkaTesting.dto.UserDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@EnableKafka
@SpringBootApplication
public class KafkaTestingApplication {

//	@KafkaListener(topics="msg")
//	public void msgListener(UserDto msg) {
//		System.out.println(msg);
//	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaTestingApplication.class, args);
	}

}
