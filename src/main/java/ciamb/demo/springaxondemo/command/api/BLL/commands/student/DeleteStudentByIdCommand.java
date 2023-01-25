package ciamb.demo.springaxondemo.command.api.BLL.commands.student;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class DeleteStudentByIdCommand {

    @TargetAggregateIdentifier
    private String studentId;

    private Integer id;

}
