package ciamb.demo.springaxondemo.command.api.BLL.events.studentevent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentEditedEvent {
    private Integer id;
    private String studentId;
    private String name;
    private String lastName;
    private LocalDate birthDate;
}
