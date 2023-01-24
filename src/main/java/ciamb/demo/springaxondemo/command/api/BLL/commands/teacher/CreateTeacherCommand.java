package ciamb.demo.springaxondemo.command.api.BLL.commands.teacher;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDate;

@Data
@Builder
public class CreateTeacherCommand {

    @TargetAggregateIdentifier
    private String teacherId;
    private String name;
    private String lastName;
    private LocalDate birthDate;
}
