package ciamb.demo.springaxondemo.command.api.handler;

import ciamb.demo.springaxondemo.command.api.events.teacherevent.TeacherCreatedEvent;
import ciamb.demo.springaxondemo.command.api.events.teacherevent.TeacherDeletedByIdEvent;
import ciamb.demo.springaxondemo.command.api.events.teacherevent.TeacherEditedEvent;
import ciamb.demo.springaxondemo.core.api.entity.Teacher;
import ciamb.demo.springaxondemo.core.api.repository.TeacherRepository;
import org.axonframework.eventhandling.EventHandler;
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

    @EventHandler
    public void on(TeacherEditedEvent teacherEditedEvent) {
        Teacher teacher = teacherRepository.findById(teacherEditedEvent.getTeacherId()).orElseThrow(EntityNotFoundException::new);
        if (!teacher.getName().isEmpty()) teacher.setName(teacherEditedEvent.getName());
        if (!teacher.getLastName().isEmpty()) teacher.setLastName(teacherEditedEvent.getLastName());
        teacherRepository.save(teacher);
    }
}
