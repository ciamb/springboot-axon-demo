package ciamb.demo.springaxondemo.command.api.commands.student;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class DeleteStudentByIdCommand {
    private Integer studentId;
    @TargetAggregateIdentifier private String id;
}
