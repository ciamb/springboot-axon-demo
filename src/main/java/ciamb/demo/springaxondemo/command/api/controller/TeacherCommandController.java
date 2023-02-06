package ciamb.demo.springaxondemo.command.api.controller;

import ciamb.demo.springaxondemo.command.api.dto.CreateTeacherRequestDto;
import ciamb.demo.springaxondemo.command.api.dto.EditTeacherRequestDto;
import ciamb.demo.springaxondemo.command.api.service.TeacherCommandService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
/*
* Il controller del Docente si occupa solo di fare la chiamata, mentre è la service che farà
* il lavoro di inviare il comando al gateway. In  un' ottica di three layer sarà questa
* la metodologia di lavoro presumo
 */
@RestController
@RequestMapping("/teacher")
@Log4j2
public class TeacherCommandController {
    private final TeacherCommandService teacherCommandService;
    public TeacherCommandController(TeacherCommandService teacherCommandService) {
        this.teacherCommandService = teacherCommandService;
    }

    @PostMapping
    public ResponseEntity<String> createTeacher(@RequestBody CreateTeacherRequestDto createTeacherRequestDto) throws ExecutionException, InterruptedException {
        log.info("E' stata richiesta la creazione di un docente!");
        CompletableFuture<String> response = teacherCommandService.createTeacher(createTeacherRequestDto);
        return new ResponseEntity<>(response.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeacherById(@PathVariable Integer id) throws ExecutionException, InterruptedException {
        log.info("E' stata richiesta l'eliminazione del docente!");
        CompletableFuture<String> response = teacherCommandService.deleteTeacherById(id);
        return new ResponseEntity<>(response.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editTeacher(@PathVariable Integer id, @RequestBody EditTeacherRequestDto editTeacherRequestDto) throws ExecutionException, InterruptedException {
        log.info("E' stata richiesta la modifica del docente con id: " + id);
        CompletableFuture<String> response = teacherCommandService.editTeacher(id, editTeacherRequestDto);
        return new ResponseEntity<>(response.get(), HttpStatus.OK);
    }
}