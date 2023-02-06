package ciamb.demo.springaxondemo.command.api.events.studentevent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDeletedEvent {
    private Integer studentId;
    private String id; // -> aggregateIdentifier
}
