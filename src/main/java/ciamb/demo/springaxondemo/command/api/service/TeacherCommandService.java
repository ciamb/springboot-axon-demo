package ciamb.demo.springaxondemo.command.api.service;

import ciamb.demo.springaxondemo.command.api.commands.teacher.CreateTeacherCommand;
import ciamb.demo.springaxondemo.command.api.commands.teacher.DeleteTeacherByIdCommand;
import ciamb.demo.springaxondemo.command.api.commands.teacher.EditTeacherCommand;
import ciamb.demo.springaxondemo.command.api.dto.CreateTeacherRequestDto;
import ciamb.demo.springaxondemo.command.api.dto.EditTeacherRequestDto;
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
        log.info("La service riceve la richiesta e la invia al gateway... questa volta senza aspettarsi una risposta");
        return commandGateway.send(new CreateTeacherCommand(
                // Settiamo come sempre il nostro identifier di questa istanza di docente!
                UUID.randomUUID().toString(),
                createTeacherRequestDto.getName(),
                createTeacherRequestDto.getLastName()
        ));
    }

    public CompletableFuture<String> deleteTeacherById(Integer id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return commandGateway.send(new DeleteTeacherByIdCommand(
                // Devo sempre fare riferimento all'aggregateIdentifier altrimenti non posso mettere in sequenza gli eventi.
                teacher.getAggregateIdentifier(),
                id
        ));
    }

    public CompletableFuture<String> editTeacher(Integer id, EditTeacherRequestDto editTeacherRequestDto) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return commandGateway.send(new EditTeacherCommand(
                teacher.getAggregateIdentifier(),
                id,
                editTeacherRequestDto.getName(),
                editTeacherRequestDto.getLastName()
        ));
    }
}
