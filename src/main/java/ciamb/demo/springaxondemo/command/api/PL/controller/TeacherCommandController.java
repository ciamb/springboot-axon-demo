package ciamb.demo.springaxondemo.command.api.PL.controller;

import ciamb.demo.springaxondemo.command.api.BLL.dto.teacher.CreateTeacherRequestDto;
import ciamb.demo.springaxondemo.command.api.BLL.dto.teacher.DeleteTeacherByIdRequestDto;
import ciamb.demo.springaxondemo.command.api.BLL.service.TeacherCommandService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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
        log.info("Siamo entrati nel metodo createTeacher del controller");
        CompletableFuture<String> response = teacherCommandService.createTeacher(createTeacherRequestDto);
        return new ResponseEntity<>(response.get(), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteTeacherById(@RequestBody DeleteTeacherByIdRequestDto deleteTeacherByIdRequestDto) throws ExecutionException, InterruptedException {
        CompletableFuture<String> response = teacherCommandService.deleteTeacherById(deleteTeacherByIdRequestDto);
        return new ResponseEntity<>(response.get(), HttpStatus.OK);
    }
}