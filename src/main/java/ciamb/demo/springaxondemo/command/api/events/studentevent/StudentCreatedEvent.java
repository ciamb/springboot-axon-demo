package ciamb.demo.springaxondemo.command.api.events.studentevent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentCreatedEvent {
    private String id; // -> aggregateIdentifier
    private String name;
    private String lastName;
    private LocalDate birthDate;
}
