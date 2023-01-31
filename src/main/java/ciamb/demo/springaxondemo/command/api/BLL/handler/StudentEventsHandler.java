package ciamb.demo.springaxondemo.command.api.BLL.handler;

import ciamb.demo.springaxondemo.command.api.BLL.events.studentevent.StudentDeletedEvent;
import ciamb.demo.springaxondemo.command.api.BLL.events.studentevent.StudentEditedEvent;
import ciamb.demo.springaxondemo.core.api.entity.Student;
import ciamb.demo.springaxondemo.command.api.BLL.events.studentevent.StudentCreatedEvent;
import ciamb.demo.springaxondemo.core.api.repository.StudentRepository;
import lombok.extern.log4j.Log4j2;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
@Log4j2
@ProcessingGroup("student")
public class StudentEventsHandler {
    private final StudentRepository studentRepository;

    public StudentEventsHandler(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /* Il metodo etichettato con @EventHandler deve avere un parametro che rappresenta l'evento che deve essere gestito.
    * Quando l'evento viene generato, il metodo etichettato con @EventHandler viene chiamato automaticamente per gestirlo.
    * @EventHandler è generico rispetto a quello che troviamo all'interno dell' aggregate. */

    @EventHandler
    public void on(StudentCreatedEvent studentCreatedEvent) {
        // All' attivazione dell'evento StudentCreatedEvent crea lo studente e lo salva all'interno del db!
        Student student =
                new Student();
        BeanUtils.copyProperties(studentCreatedEvent, student);
        studentRepository.save(student);
        log.info("Studente aggiunto correttamente nel db! id: {}", student.getId());
    }

    // Queste exception sono gestite dall'handler del processinggroup student
    @EventHandler
    public void on(StudentDeletedEvent studentDeletedEvent) {
            Student student =
                    studentRepository.findById(studentDeletedEvent.getId()).orElseThrow(EntityNotFoundException::new);
            BeanUtils.copyProperties(studentDeletedEvent, student);
            studentRepository.deleteById(student.getId());
    }

    @EventHandler
    public void on(StudentEditedEvent studentEditedEvent) {
            Student student =
                    studentRepository.findById(studentEditedEvent.getId()).orElseThrow(EntityNotFoundException::new);
            if (studentEditedEvent.getName() != null) student.setName(studentEditedEvent.getName());
            if (studentEditedEvent.getLastName() != null) student.setLastName(studentEditedEvent.getLastName());
            if (studentEditedEvent.getBirthDate() != null) student.setBirthDate(studentEditedEvent.getBirthDate());
            studentRepository.save(student);
    }

}
