package ciamb.demo.springaxondemo.command.api.BLL.aggregate;

import ciamb.demo.springaxondemo.command.api.BLL.commands.teacher.CreateTeacherCommand;
import ciamb.demo.springaxondemo.command.api.BLL.commands.teacher.DeleteTeacherByIdCommand;
import ciamb.demo.springaxondemo.command.api.BLL.events.teacherevent.TeacherCreatedEvent;
import ciamb.demo.springaxondemo.command.api.BLL.events.teacherevent.TeacherDeletedByIdEvent;
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

    @AggregateIdentifier
    private String id;
    private Integer teacherId;
    private String name;
    private String lastName;


    /*
    *    CREATE
    */
    @CommandHandler
    public TeacherAggregate(CreateTeacherCommand createTeacherCommand) {
        log.info("Ricevuto createTeacherCommand nel CommandHandler!");

        //Qui è possibile fare le validazioni

        AggregateLifecycle.apply(new TeacherCreatedEvent(
                createTeacherCommand.getId(),
                createTeacherCommand.getName(),
                createTeacherCommand.getLastName()
        ));
    }

    @EventSourcingHandler
    public void on(TeacherCreatedEvent teacherCreatedEvent) {
        log.info("Creato evento teacherCreatedEvent nel metodo on dell'aggregate.");
        this.id = teacherCreatedEvent.getEventId();
        this.name = teacherCreatedEvent.getName();
        this.lastName = teacherCreatedEvent.getLastName();
    }

    /*
    * DELETE
    */
    @CommandHandler
    public void on(DeleteTeacherByIdCommand deleteTeacherByIdCommand) {
        AggregateLifecycle.apply(new TeacherDeletedByIdEvent(
                deleteTeacherByIdCommand.getId(),
                deleteTeacherByIdCommand.getTeacherId()
        ));
    }

    @EventSourcingHandler
    public void on(TeacherDeletedByIdEvent teacherDeletedByIdEvent) {
        this.teacherId = teacherDeletedByIdEvent.getTeacherId();
        this.id = teacherDeletedByIdEvent.getEventId();
    }

}
