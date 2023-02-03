package ciamb.demo.springaxondemo.command.api.BLL.handler;

import ciamb.demo.springaxondemo.command.api.BLL.events.teacherevent.TeacherCreatedEvent;
import ciamb.demo.springaxondemo.command.api.BLL.events.teacherevent.TeacherDeletedByIdEvent;
import ciamb.demo.springaxondemo.core.api.entity.Teacher;
import ciamb.demo.springaxondemo.core.api.repository.TeacherRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class TeacherEventsHandler {
    private final TeacherRepository teacherRepository;

    public TeacherEventsHandler(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @EventHandler
    public void on(TeacherCreatedEvent teacherCreatedEvent) {
        Teacher teacher =
                Teacher.builder()
                        .aggregateIdentifier(teacherCreatedEvent.getId())
                        .name(teacherCreatedEvent.getName())
                        .lastName(teacherCreatedEvent.getLastName())
                        .build();
        teacherRepository.save(teacher);
    }

    @EventHandler
    public void on(TeacherDeletedByIdEvent teacherDeletedByIdEvent) {
        teacherRepository.deleteById(teacherDeletedByIdEvent.getTeacherId());
    }
}
