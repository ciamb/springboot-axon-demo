package ciamb.demo.springaxondemo.command.api.PL.controller;

import ciamb.demo.springaxondemo.command.api.BLL.commands.teacher.CreateTeacherCommand;
import ciamb.demo.springaxondemo.core.api.rest.TeacherRest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/teacher")
public class TeacherCommandController {
    private final CommandGateway commandGateway;

    public TeacherCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String addTeacher(@RequestBody TeacherRest teacherRest) {
        CreateTeacherCommand createTeacherCommand =
                CreateTeacherCommand.builder()
                        .teacherId(UUID.randomUUID().toString())
                        .name(teacherRest.getName())
                        .lastName(teacherRest.getLastName())
                        .birthDate(teacherRest.getBirthDate())
                        .build();
    return commandGateway.sendAndWait(createTeacherCommand);
    }
}
