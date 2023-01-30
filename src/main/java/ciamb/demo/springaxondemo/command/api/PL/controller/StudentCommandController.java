package ciamb.demo.springaxondemo.command.api.PL.controller;

import ciamb.demo.springaxondemo.command.api.BLL.commands.student.CreateStudentCommand;
import ciamb.demo.springaxondemo.command.api.BLL.commands.student.DeleteStudentByIdCommand;
import ciamb.demo.springaxondemo.command.api.BLL.commands.student.EditStudentCommand;
import ciamb.demo.springaxondemo.core.api.rest.StudentRest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> addStudent(@RequestBody StudentRest studentRest) {
        try {
            CreateStudentCommand createStudentCommand =
                    CreateStudentCommand.builder()
                            .studentId(UUID.randomUUID().toString())
                            .name(studentRest.getName())
                            .lastName(studentRest.getLastName())
                            .birthDate(studentRest.getBirthDate())
                            .build();
            commandGateway.sendAndWait(createStudentCommand);
            return new ResponseEntity<>("Evento pubblicato con successo!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Qualcosa è andato storto.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteStudentById(@RequestBody Integer id) {
        try {
            DeleteStudentByIdCommand deleteStudentByIdCommand =
                    DeleteStudentByIdCommand.builder()
                            .studentId(UUID.randomUUID().toString())
                            .id(id)
                            .build();
            commandGateway.sendAndWait(deleteStudentByIdCommand);
            return new ResponseEntity<>("Evento pubblicato con successo!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Qualcosa è andato storto.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> editStudent(@PathVariable Integer id, @RequestBody StudentRest studentRest) {
        try {
            EditStudentCommand editStudentCommand =
                    EditStudentCommand.builder()
                            .id(id)
                            .studentId(UUID.randomUUID().toString())
                            .name(studentRest.getName())
                            .lastName(studentRest.getLastName())
                            .birthDate(studentRest.getBirthDate())
                            .build();
            commandGateway.sendAndWait(editStudentCommand);
            return new ResponseEntity<>("Evento pubblicato con successo!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Qualcosa è andato storto.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
