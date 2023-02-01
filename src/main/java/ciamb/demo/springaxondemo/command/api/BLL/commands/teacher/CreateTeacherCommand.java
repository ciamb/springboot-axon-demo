package ciamb.demo.springaxondemo.command.api.BLL.commands.teacher;

import ciamb.demo.springaxondemo.command.api.BLL.commands.BaseCommand;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDate;

@Getter
@Setter
public class CreateTeacherCommand extends BaseCommand<String> {

    public CreateTeacherCommand(String id, String name, String lastName) {
        super(id);
        this.name = name;
        this.lastName = lastName;
    }
   private final String name;
   private final String lastName;

}
