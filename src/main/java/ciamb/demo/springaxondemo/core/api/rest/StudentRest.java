package ciamb.demo.springaxondemo.core.api.rest;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Data
@Builder
public class StudentRest {
    private Integer id;
    private String name;
    private String lastName;
    private LocalDate birthDate;

}
