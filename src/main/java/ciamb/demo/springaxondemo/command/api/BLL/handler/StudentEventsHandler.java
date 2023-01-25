package ciamb.demo.springaxondemo.command.api.BLL.handler;

import ciamb.demo.springaxondemo.command.api.BLL.events.studentevent.StudentDeletedEvent;
import ciamb.demo.springaxondemo.core.api.entity.Student;
import ciamb.demo.springaxondemo.command.api.BLL.events.studentevent.StudentCreatedEvent;
import ciamb.demo.springaxondemo.core.api.repository.StudentRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class StudentEventsHandler {
    private final StudentRepository studentRepository;

    public StudentEventsHandler(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Il metodo etichettato con @EventHandler deve avere un parametro che rappresenta l'evento che deve essere gestito.
    // Quando l'evento viene generato, il metodo etichettato con @EventHandler viene chiamato automaticamente per gestirlo.
    // @EventHandler è generico rispetto a quello che troviamo all'interno dell' aggregate.
    @EventHandler
    public void handle(StudentCreatedEvent studentCreatedEvent) {

        // All' attivazione dell'evento StudentCreatedEvent crea lo studente e lo salva all'interno del db!
        Student student =
                new Student();
        BeanUtils.copyProperties(studentCreatedEvent, student);
        studentRepository.save(student);
    }

    @EventHandler
    public void handle(StudentDeletedEvent studentDeletedEvent) {

        Student student =
                new Student();
        BeanUtils.copyProperties(studentDeletedEvent, student);
        studentRepository.deleteById(student.getId());
    }

}
