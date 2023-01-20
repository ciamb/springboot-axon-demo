package ciamb.demo.springaxondemo.command.api.BLL.handler;

import ciamb.demo.springaxondemo.core.api.entity.Student;
import ciamb.demo.springaxondemo.command.api.BLL.events.studentevent.StudentCreatedEvent;
import ciamb.demo.springaxondemo.core.api.repository.StudentRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class StudentEventsHandler {
    private final ciamb.demo.springaxondemo.core.api.repository.StudentRepository StudentRepository;


    public StudentEventsHandler(StudentRepository StudentRepository) {
        this.StudentRepository = StudentRepository;
    }

    @EventHandler
    public void handle(StudentCreatedEvent studentCreatedEvent) {
    Student student =
            new Student();
    BeanUtils.copyProperties(studentCreatedEvent, student);
    StudentRepository.save(student);
    }
}
