package ciamb.demo.springaxondemo.command.api.BLL.commands.student;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDate;

@Data
@Builder
public class CreateStudentCommand {
    /*
    * Devo specificare il TargetAggregateIdentifier tramite apposita annotazione, in modo da dare un identificatore
    * univoco all'evento che stiamo per creare.
    */
    @TargetAggregateIdentifier private String id;
    private String name;
    private String lastName;
    private LocalDate birthDate;
}
