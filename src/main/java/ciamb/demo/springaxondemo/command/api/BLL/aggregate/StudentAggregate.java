package ciamb.demo.springaxondemo.command.api.BLL.aggregate;

import ciamb.demo.springaxondemo.command.api.BLL.commands.student.CreateStudentCommand;
import ciamb.demo.springaxondemo.command.api.BLL.commands.student.DeleteStudentByIdCommand;
import ciamb.demo.springaxondemo.command.api.BLL.commands.student.EditStudentCommand;
import ciamb.demo.springaxondemo.command.api.BLL.events.studentevent.StudentCreatedEvent;
import ciamb.demo.springaxondemo.command.api.BLL.events.studentevent.StudentDeletedEvent;
import ciamb.demo.springaxondemo.command.api.BLL.events.studentevent.StudentEditedEvent;
import ciamb.demo.springaxondemo.core.api.exception.EmptyNameExcpetion;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

 /*
 * Per l'Aggregate devo mettere l'annotations apposita @Aggregate e specificare l'@AggregateIdentifier sul campo
 * che si aspetta in arrivo e che corrisponda al @TargetAggregateIdentifier
  */
@Aggregate
@NoArgsConstructor
@Log4j2
public class StudentAggregate {
    private Integer studentId;
    @AggregateIdentifier
    private String id;
    private String name;
    private String lastName;
    private LocalDate birthDate;

    /*
    * Quando il CommandGateway invia un comando al sistema, Axon cerca tra tutti i metodi con l'annotazione
    * @CommandHandler per trovare quello che è in grado di gestire il comando inviato.
    * Una volta individuato il metodo corretto, Axon esegue il metodo passando il comando come parametro.
     */

     /*
     * CREATE - NB: Per la creazione posso/devo andare ad utilizzare il costruttore in quanto
     * serve creare la prima istanza con l'aggregateIdentifier
     * a cui poi farò riferimento per tutte le altre operazioni!
     * */
    @CommandHandler
    public StudentAggregate(CreateStudentCommand createStudentCommand) throws Exception{
        log.info("E' arrivato un comando per la creazione dello studente...");

        // Qui è possibile fare le validazioni del caso. Es:
        if (createStudentCommand.getName() == null || createStudentCommand.getName().isEmpty()) {
            throw new EmptyNameExcpetion();
        }

        StudentCreatedEvent studentCreatedEvent = StudentCreatedEvent.builder()
                .id(createStudentCommand.getId())
                .name(createStudentCommand.getName())
                .lastName(createStudentCommand.getLastName())
                .birthDate(createStudentCommand.getBirthDate())
                .build();
        AggregateLifecycle.apply(studentCreatedEvent);
        /*
        * Serve per registrare eventi all'interno di un Aggregate e per pubblicare
        * gli eventi registrati nel sistema di messaggistica, in modo da informare altri
        * componenti del sistema delle modifiche apportate allo stato dell'Aggregate.
         */
        log.info("Fatto l'apply dell'evento di creazione.");
    }
    /*
    * Quando un evento viene pubblicato nel sistema di messaggistica, Axon cerca tra tutti
    * i metodi annotati con @EventSourcingHandler per trovare quello che è in grado
    * di gestire l'evento pubblicato. Una volta individuato il metodo corretto, Axon
    * esegue il metodo e passa l'evento come parametro.
     */
    @EventSourcingHandler
    public void on(StudentCreatedEvent studentCreatedEvent) {
        log.info("Riempiendo l'aggregate con i dati in arrivo dall'evento.");
        this.id = studentCreatedEvent.getId();
        this.name = studentCreatedEvent.getName();
        this.lastName = studentCreatedEvent.getLastName();
        this.birthDate = studentCreatedEvent.getBirthDate();
    }

    /*
    * DELETE -
     */
    @CommandHandler
    public void handle(DeleteStudentByIdCommand deleteStudentByIdCommand) {
        StudentDeletedEvent studentDeletedEvent = new StudentDeletedEvent();
        BeanUtils.copyProperties(deleteStudentByIdCommand, studentDeletedEvent);
        AggregateLifecycle.apply(studentDeletedEvent);
    }

    @EventSourcingHandler
    public void on(StudentDeletedEvent studentDeletedEvent) {
        this.studentId = studentDeletedEvent.getStudentId();
        this.id = studentDeletedEvent.getId();
    }

    /*
    * EDIT -
     */
    @CommandHandler
    public void handle(EditStudentCommand editStudentCommand) {
        log.info("Gestendo l'evento di modifica.");
        StudentEditedEvent studentEditEvent = new StudentEditedEvent();
        BeanUtils.copyProperties(editStudentCommand, studentEditEvent);
        AggregateLifecycle.apply(studentEditEvent);
    }
    @EventSourcingHandler
    public void on(StudentEditedEvent studentEditedEvent) {
        log.info("Setto le informazioni in arrivo dall'evento all'aggregate.");
        this.studentId = studentEditedEvent.getStudentId();
        this.id = studentEditedEvent.getId();
        this.name = studentEditedEvent.getName();
        this.lastName = studentEditedEvent.getLastName();
        this.birthDate = studentEditedEvent.getBirthDate();
    }

}
