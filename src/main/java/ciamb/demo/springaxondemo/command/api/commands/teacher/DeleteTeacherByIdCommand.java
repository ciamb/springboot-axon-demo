package ciamb.demo.springaxondemo.command.api.commands.teacher;

import ciamb.demo.springaxondemo.command.api.commands.BaseCommand;
import lombok.Getter;

@Getter
public class DeleteTeacherByIdCommand extends BaseCommand<String> {
    private final Integer teacherId;
    public DeleteTeacherByIdCommand(String id, Integer teacherId) {
        super(id);
        this.teacherId = teacherId;
    }
}
