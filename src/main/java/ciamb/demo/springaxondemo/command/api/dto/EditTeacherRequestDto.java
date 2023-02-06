package ciamb.demo.springaxondemo.command.api.dto;

import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class EditTeacherRequestDto {
    private String name;
    private String lastName;
}
