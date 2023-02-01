package ciamb.demo.springaxondemo.command.api.BLL.service;

import ciamb.demo.springaxondemo.command.api.BLL.commands.teacher.CreateTeacherCommand;
import ciamb.demo.springaxondemo.command.api.BLL.commands.teacher.DeleteTeacherByIdCommand;
import ciamb.demo.springaxondemo.command.api.BLL.dto.teacher.CreateTeacherRequestDto;
import ciamb.demo.springaxondemo.command.api.BLL.dto.teacher.DeleteTeacherByIdRequestDto;
import ciamb.demo.springaxondemo.core.api.entity.Teacher;
import ciamb.demo.springaxondemo.core.api.repository.TeacherRepository;
import lombok.extern.log4j.Log4j2;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@Log4j2
public class TeacherCommandService {

    private final CommandGateway commandGateway;
    private final TeacherRepository teacherRepository;

    public TeacherCommandService(CommandGateway commandGateway, TeacherRepository teacherRepository) {
        this.commandGateway = commandGateway;
        this.teacherRepository = teacherRepository;
    }

    public CompletableFuture<String> createTeacher(CreateTeacherRequestDto createTeacherRequestDto) {
        log.info("Qua siamo nel TeacherCommandService dove viene passata la request e inviato un nuovo comando di create!");
        return commandGateway.send(new CreateTeacherCommand(
                // andiamo a settare il nostro identifier
                UUID.randomUUID().toString(),
                createTeacherRequestDto.getName(),
                createTeacherRequestDto.getLastName()
        ));
    }

    public CompletableFuture<String> deleteTeacherById(DeleteTeacherByIdRequestDto deleteTeacherByIdRequestDto) {
        return commandGateway.send(new DeleteTeacherByIdCommand(
                UUID.randomUUID().toString(),
                deleteTeacherByIdRequestDto.getTeacherId()
        ));

    }
}
