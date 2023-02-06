package ciamb.demo.springaxondemo.command.api.aggregate;

import ciamb.demo.springaxondemo.command.api.commands.teacher.CreateTeacherCommand;
import ciamb.demo.springaxondemo.command.api.commands.teacher.DeleteTeacherByIdCommand;
import ciamb.demo.springaxondemo.command.api.commands.teacher.EditTeacherCommand;
import ciamb.demo.springaxondemo.command.api.events.teacherevent.TeacherCreatedEvent;
import ciamb.demo.springaxondemo.command.api.events.teacherevent.TeacherDeletedByIdEvent;
import ciamb.demo.springaxondemo.command.api.events.teacherevent.TeacherEditedEvent;
import ciamb.demo.springaxondemo.core.api.exception.EmptyNameExcpetion;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@NoArgsConstructor
@Log4j2
public class TeacherAggregate {

    private Integer teacherId;
    @AggregateIdentifier private String id;
    private String name;
    private String lastName;


    /*
    * CREATE
    */
    @CommandHandler
    public TeacherAggregate(CreateTeacherCommand createTeacherCommand) throws Exception{
        log.info("Ricevuto createTeacherCommand nel CommandHandler!");

        //Qui è possibile fare le validazioni. ES
        if (createTeacherCommand.getName().isEmpty()) throw new EmptyNameExcpetion(); // -> probabilmente darà una nested exception

        AggregateLifecycle.apply(new TeacherCreatedEvent(
                createTeacherCommand.getId(),
                createTeacherCommand.getName(),
                createTeacherCommand.getLastName()
        ));
    }

    @EventSourcingHandler
    public void on(TeacherCreatedEvent teacherCreatedEvent) {
        log.info("Assegno ai campi dell' aggregate quelli in arrivo dall' evento! (Riempito a sua volta da quelli arrivati dal comando)");
        this.id = teacherCreatedEvent.getId();
        this.name = teacherCreatedEvent.getName();
        this.lastName = teacherCreatedEvent.getLastName();
    }

    /*
    * DELETE
    */
    @CommandHandler
    public void handle(DeleteTeacherByIdCommand deleteTeacherByIdCommand) {
        log.info("Ricevuto deleteTeacherByIdCommand nel CommandHandler!");

        AggregateLifecycle.apply(new TeacherDeletedByIdEvent(
                deleteTeacherByIdCommand.getId(),
                deleteTeacherByIdCommand.getTeacherId()
        ));
    }

    @EventSourcingHandler
    public void on(TeacherDeletedByIdEvent teacherDeletedByIdEvent) {
        log.info("Assegno al teacherId dell'aggregate il teacherId del docente da eliminare...");
        this.teacherId = teacherDeletedByIdEvent.getTeacherId();
    }

    /*
     * PUT
     */
    @CommandHandler
    public void handle(EditTeacherCommand editTeacherCommand) {
        log.info("Ricevuta richiesta di modifica.");

        AggregateLifecycle.apply(new TeacherEditedEvent(
           editTeacherCommand.getId(),
           editTeacherCommand.getTeacherId(),
           editTeacherCommand.getName(),
           editTeacherCommand.getLastName()
        ));
    }

    @EventSourcingHandler
    public void on(TeacherEditedEvent teacherEditedEvent) {
        log.info("Nell' @EventSourcingHandler dell' edit.");
        this.name = teacherEditedEvent.getName();
        this.lastName = teacherEditedEvent.getLastName();
    }
}
