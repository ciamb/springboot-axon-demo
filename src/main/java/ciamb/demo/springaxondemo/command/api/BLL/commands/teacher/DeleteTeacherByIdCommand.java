package ciamb.demo.springaxondemo.command.api.BLL.commands.teacher;

import ciamb.demo.springaxondemo.command.api.BLL.commands.BaseCommand;
import lombok.Getter;

@Getter
public class DeleteTeacherByIdCommand extends BaseCommand<String> {

    public DeleteTeacherByIdCommand(String id, Integer teacherId) {
        super(id);
        this.teacherId = teacherId;
    }

    private final Integer teacherId;
}
