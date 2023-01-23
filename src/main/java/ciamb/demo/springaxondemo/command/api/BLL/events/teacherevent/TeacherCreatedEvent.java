package ciamb.demo.springaxondemo.command.api.BLL.events.teacherevent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherCreatedEvent {
    private String teacherId;
    private String name;
    private String lastName;
    private LocalDate birthDate;
}
