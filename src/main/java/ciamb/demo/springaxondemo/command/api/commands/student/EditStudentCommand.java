package ciamb.demo.springaxondemo.command.api.commands.student;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDate;

@Data
@Builder
public class EditStudentCommand {
    private Integer studentId;
    @TargetAggregateIdentifier private String id;
    private String name;
    private String lastName;
    private LocalDate birthDate;
}
