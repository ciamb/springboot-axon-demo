package ciamb.demo.springaxondemo.command.api.BLL.aggregate;

import ciamb.demo.springaxondemo.command.api.BLL.commands.student.CreateStudentCommand;
import ciamb.demo.springaxondemo.command.api.BLL.commands.student.DeleteStudentByIdCommand;
import ciamb.demo.springaxondemo.command.api.BLL.commands.student.EditStudentCommand;
import ciamb.demo.springaxondemo.command.api.BLL.events.studentevent.StudentCreatedEvent;
import ciamb.demo.springaxondemo.command.api.BLL.events.studentevent.StudentDeletedEvent;
import ciamb.demo.springaxondemo.command.api.BLL.events.studentevent.StudentEditedEvent;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

// Per l'Aggregate devo mettere l'annotations apposita @Aggregate e specificare l'@AggregateIdentifier sul campo
// che si aspetta in arrivo e che corrisponda al @TargetAggregateIdentifier
@Aggregate
@NoArgsConstructor
@Log4j2
public class StudentAggregate {
    @AggregateIdentifier
    private String studentId;

    private Integer id;
    private String name;
    private String lastName;
    private LocalDate birthDate;

    // Quando il Command Gateway invia un comando al sistema, Axon cerca tra tutti i metodi annotati
    // con @CommandHandler per trovare quello che è in grado di gestire il comando inviato.
    // Una volta individuato il metodo corretto,
    // Axon esegue il metodo e passa il comando come parametro.
    @CommandHandler
    public StudentAggregate(CreateStudentCommand createStudentCommand) {

        //Qui è possibile fare tutte la validazioni e poi viene creato l'evento a cui fa riferimento questo aggregate!

        StudentCreatedEvent studentCreatedEvent =
                new StudentCreatedEvent();
        BeanUtils.copyProperties(createStudentCommand, studentCreatedEvent);
        AggregateLifecycle.apply(studentCreatedEvent);
        // Serve per registrare eventi all'interno di un Aggregate e per pubblicare
        // gli eventi registrati nel sistema di messaggistica, in modo da informare altri
        // componenti del sistema delle modifiche apportate allo stato dell'Aggregate.
        log.info("Evento pubblicato! aggregateIdentifier = \"{}\"", studentCreatedEvent.getStudentId());

    }

    @CommandHandler
    public StudentAggregate(DeleteStudentByIdCommand deleteStudentByIdCommand) {

        // validazioni

        StudentDeletedEvent studentDeletedEvent =
                new StudentDeletedEvent();
        BeanUtils.copyProperties(deleteStudentByIdCommand, studentDeletedEvent);
        AggregateLifecycle.apply(studentDeletedEvent);
        log.info("Evento pubblicato! aggregateIdentifier = \"{}\"", studentDeletedEvent.getStudentId());

    }

    @CommandHandler
    public StudentAggregate(EditStudentCommand editStudentCommand) {

        // validazioni

        StudentEditedEvent studentEditEvent =
                new StudentEditedEvent();
        BeanUtils.copyProperties(editStudentCommand, studentEditEvent);
        AggregateLifecycle.apply(studentEditEvent);
        log.info("Evento pubblicato! aggregateIdentifier = \"{}\"", studentEditEvent.getStudentId());

    }

    // Quando un evento viene pubblicato nel sistema di messaggistica, Axon cerca tra tutti
    // i metodi annotati con @EventSourcingHandler per trovare quello che è in grado
    // di gestire l'evento pubblicato. Una volta individuato il metodo corretto, Axon
    // esegue il metodo e passa l'evento come parametro.
    @EventSourcingHandler
    public void on(StudentCreatedEvent studentCreatedEvent) {
        this.studentId = studentCreatedEvent.getStudentId();
        this.name = studentCreatedEvent.getName();
        this.lastName = studentCreatedEvent.getLastName();
        this.birthDate = studentCreatedEvent.getBirthDate();
    }

    @EventSourcingHandler
    public void on(StudentDeletedEvent studentDeletedEvent) {
        this.id = studentDeletedEvent.getId();
        this.studentId = studentDeletedEvent.getStudentId();
    }

    @EventSourcingHandler
    public void on(StudentEditedEvent studentEditedEvent) {
        this.id = studentEditedEvent.getId();
        this.studentId = studentEditedEvent.getStudentId();
        this.name = studentEditedEvent.getName();
        this.lastName = studentEditedEvent.getLastName();
        this.birthDate = studentEditedEvent.getBirthDate();
    }

}
