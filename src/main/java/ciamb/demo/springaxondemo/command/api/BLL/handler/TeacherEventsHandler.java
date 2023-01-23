package ciamb.demo.springaxondemo.command.api.BLL.handler;

import ciamb.demo.springaxondemo.command.api.BLL.events.teacherevent.TeacherCreatedEvent;
import ciamb.demo.springaxondemo.core.api.entity.Teacher;
import ciamb.demo.springaxondemo.core.api.repository.TeacherRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class TeacherEventsHandler {
    private final TeacherRepository teacherRepository;

    public TeacherEventsHandler(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @EventHandler
    public void handle(TeacherCreatedEvent teacherCreatedEvent) {
        Teacher teacher =
                new Teacher();
        BeanUtils.copyProperties(teacherCreatedEvent, teacher);
        teacherRepository.save(teacher);
    }
}
