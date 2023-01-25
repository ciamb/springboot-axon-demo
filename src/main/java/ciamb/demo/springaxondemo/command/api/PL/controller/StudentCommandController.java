package ciamb.demo.springaxondemo.command.api.PL.controller;

import ciamb.demo.springaxondemo.command.api.BLL.commands.student.CreateStudentCommand;
import ciamb.demo.springaxondemo.command.api.BLL.commands.student.DeleteStudentByIdCommand;
import ciamb.demo.springaxondemo.core.api.entity.Student;
import ciamb.demo.springaxondemo.core.api.rest.StudentRest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/student")
public class StudentCommandController {
    // Mi inietto il CommandGateway che fa da cancello per le chiamate verso i vari servizi
    // (in un ambiente a microservizi) ma nel nostro caso fa solo da dispatcher.
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

    @DeleteMapping
    public String deleteStudentById(@RequestBody Integer id) {
        DeleteStudentByIdCommand deleteStudentByIdCommand =
                DeleteStudentByIdCommand.builder()
                        .studentId(UUID.randomUUID().toString())
                        .id(id)
                        .build();
        return commandGateway.sendAndWait(deleteStudentByIdCommand);
    }

}
