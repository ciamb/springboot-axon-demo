package ciamb.demo.springaxondemo.command.api.commands.teacher;

import ciamb.demo.springaxondemo.command.api.commands.BaseCommand;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditTeacherCommand extends BaseCommand<String> {
    private final Integer teacherId;
    private final String name;
    private final String lastName;

    public EditTeacherCommand(String id, Integer teacherId, String name, String lastName) {
        super(id);
        this.teacherId = teacherId;
        this.name = name;
        this.lastName = lastName;
    }
}