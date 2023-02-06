package ciamb.demo.springaxondemo.command.api.commands.teacher;

import ciamb.demo.springaxondemo.command.api.commands.BaseCommand;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTeacherCommand extends BaseCommand<String> {


   private final String name;
   private final String lastName;
   public CreateTeacherCommand(String id, String name, String lastName) {
       super(id);
       this.name = name;
       this.lastName = lastName;
   }
}