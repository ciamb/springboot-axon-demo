package ciamb.demo.springaxondemo.core.api.rest;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class TeacherRest {
    private String name;
    private String lastName;
    private LocalDate birthDate;
}
