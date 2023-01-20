package ciamb.demo.springaxondemo.command.api.PL.controller;

import ciamb.demo.springaxondemo.command.api.BLL.commands.student.CreateStudentCommand;
import ciamb.demo.springaxondemo.core.api.rest.StudentRest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/student")
public class StudentCommandController {
    private final CommandGateway commandGateway;

    public StudentCommandController(CommandGateway commandGateway){
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String addStudent(@RequestBody StudentRest studentRest) {
        CreateStudentCommand createStudentCommand =
                CreateStudentCommand.builder()
                        .studentId(UUID.randomUUID().toString())
                        .name(studentRest.getName())
                        .lastName(studentRest.getLastName())
                        .birthDate(studentRest.getBirthDate())
                        .build();
    return commandGateway.sendAndWait(createStudentCommand);
    }
}
