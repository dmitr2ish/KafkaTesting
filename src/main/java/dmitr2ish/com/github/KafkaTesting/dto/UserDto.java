package dmitr2ish.com.github.KafkaTesting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UserDto {
    private Long age;
    private String name;
    private Address address;
}