package ciamb.demo.springaxondemo.command.api.BLL.aggregate;

import ciamb.demo.springaxondemo.command.api.BLL.commands.teacher.CreateTeacherCommand;
import ciamb.demo.springaxondemo.command.api.BLL.events.teacherevent.TeacherCreatedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

@Aggregate
@NoArgsConstructor
public class TeacherAggregate {

    @AggregateIdentifier
    private String teacherId;
    private String name;
    private String lastName;
    private LocalDate birthDate;

    @CommandHandler
    public TeacherAggregate(CreateTeacherCommand createTeacherCommand) {

        //Qui è possibile fare le validazioni

        TeacherCreatedEvent teacherCreatedEvent =
                new TeacherCreatedEvent();
        BeanUtils.copyProperties(createTeacherCommand, teacherCreatedEvent);
        AggregateLifecycle.apply(teacherCreatedEvent);
    }

    @EventSourcingHandler
    public void on(TeacherCreatedEvent teacherCreatedEvent) {
        this.teacherId = teacherCreatedEvent.getTeacherId();
        this.name = teacherCreatedEvent.getName();
        this.lastName = teacherCreatedEvent.getLastName();
        this.birthDate = teacherCreatedEvent.getBirthDate();
    }
}
