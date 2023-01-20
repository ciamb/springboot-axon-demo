package ciamb.demo.springaxondemo.command.api.BLL.commands.student;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDate;

@Data
@Builder
public class CreateStudentCommand {

    @TargetAggregateIdentifier
    private String studentId;
    private String name;
    private String lastName;
    private LocalDate birthDate;
}
