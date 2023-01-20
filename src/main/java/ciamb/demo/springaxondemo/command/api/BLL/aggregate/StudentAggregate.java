package ciamb.demo.springaxondemo.command.api.BLL.aggregate;

import ciamb.demo.springaxondemo.command.api.BLL.commands.student.CreateStudentCommand;
import ciamb.demo.springaxondemo.command.api.BLL.events.studentevent.StudentCreatedEvent;
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
public class StudentAggregate {
    @AggregateIdentifier
    private String studentId;
    private String name;
    private String lastName;
    private LocalDate birthDate;

    @CommandHandler
    public StudentAggregate(CreateStudentCommand createStudentCommand) {

        //Qui è possibile fare tutte la validazioni

        StudentCreatedEvent studentCreatedEvent =
                new StudentCreatedEvent();
        BeanUtils.copyProperties(createStudentCommand, studentCreatedEvent);
        AggregateLifecycle.apply(studentCreatedEvent);
    }

    @EventSourcingHandler
    public void on(StudentCreatedEvent studentCreatedEvent) {
        this.studentId = studentCreatedEvent.getStudentId();
        this.name = studentCreatedEvent.getName();
        this.lastName = studentCreatedEvent.getLastName();
        this.birthDate = studentCreatedEvent.getBirthDate();
    }
}
