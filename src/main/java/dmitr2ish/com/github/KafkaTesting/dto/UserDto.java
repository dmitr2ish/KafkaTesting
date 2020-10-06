package dmitr2ish.com.github.KafkaTesting.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {
    private String name;
    private String surname;
    private Integer passportSeries;
    private Integer passportNumber;
    private String  whoIssuedPassport;
    private LocalDate whenIssuedPassport;
    private String divisionCode;
    
    
}