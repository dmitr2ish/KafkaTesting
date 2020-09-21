package dmitr2ish.com.github.KafkaTesting.dto;

import lombok.Data;

@Data
public class UserDto {
    private Integer age;
    private String name;
    private Information someinformation;
}