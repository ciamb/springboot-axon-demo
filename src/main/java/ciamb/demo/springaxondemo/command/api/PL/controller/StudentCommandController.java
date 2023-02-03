package ciamb.demo.springaxondemo.command.api.PL.controller;

import ciamb.demo.springaxondemo.command.api.BLL.commands.student.CreateStudentCommand;
import ciamb.demo.springaxondemo.command.api.BLL.commands.student.DeleteStudentByIdCommand;
import ciamb.demo.springaxondemo.command.api.BLL.commands.student.EditStudentCommand;
import ciamb.demo.springaxondemo.core.api.entity.Student;
import ciamb.demo.springaxondemo.core.api.repository.StudentRepository;
import ciamb.demo.springaxondemo.core.api.rest.StudentRest;
import lombok.extern.log4j.Log4j2;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

/*
* Il controller dello studente è stato gestito senza una service che facesse il lavoro "sporco"
* quindi risulta più goffo rispetto al controller del docente (è stato fatto prima).
*/
@RestController
@RequestMapping("/student")
@Log4j2
public class StudentCommandController {
    /*
    * Mi inietto il CommandGateway che fa da cancello per le chiamate verso i vari servizi
    * (in un ambiente a microservizi) ma nel nostro caso fa solo da dispatcher.
    */
    private final CommandGateway commandGateway;
    private final StudentRepository studentRepository;

    public StudentCommandController(CommandGateway commandGateway, StudentRepository studentRepository){
        this.commandGateway = commandGateway;
        this.studentRepository = studentRepository;
    }

    @PostMapping
    public ResponseEntity<String> addStudent(@RequestBody StudentRest studentRest) {
        CreateStudentCommand createStudentCommand =
                CreateStudentCommand.builder()
                        .id(UUID.randomUUID().toString())
                        .name(studentRest.getName())
                        .lastName(studentRest.getLastName())
                        .birthDate(studentRest.getBirthDate())
                        .build();
        log.info("Comando inviato al commandGateway che aspetta...");
        String response = commandGateway.sendAndWait(createStudentCommand);
        log.info("Pubblicazione evento avvenuta!");
        return new ResponseEntity<>("aggregateIdentifier = \"" + response + "\"", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable Integer id) {
        Student student = studentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        DeleteStudentByIdCommand deleteStudentByIdCommand =
                DeleteStudentByIdCommand.builder()
                        .studentId(id)
                        .id(student.getAggregateIdentifier())
                        .build();
        log.info("Comando inviato al commandGateway che aspetta...");
        commandGateway.sendAndWait(deleteStudentByIdCommand);
        log.info("Pubblicazione evento avvenuta!");
        return new ResponseEntity<>("Eliminato lo studente con id: " + id, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> editStudent(@PathVariable Integer id, @RequestBody StudentRest studentRest) {
        Student student = studentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        EditStudentCommand editStudentCommand =
                EditStudentCommand.builder()
                        .studentId(id)
                        .id(student.getAggregateIdentifier())
                        .name(studentRest.getName())
                        .lastName(studentRest.getLastName())
                        .birthDate(studentRest.getBirthDate())
                        .build();
        log.info("Comando inviato al commandGateway che aspetta...");
        commandGateway.sendAndWait(editStudentCommand);
        log.info("Aggiunto l'evento nell' EventStore.");
        return new ResponseEntity<>("Aggiornato lo studente con id: " + editStudentCommand.getStudentId(), HttpStatus.OK);
    }
}
