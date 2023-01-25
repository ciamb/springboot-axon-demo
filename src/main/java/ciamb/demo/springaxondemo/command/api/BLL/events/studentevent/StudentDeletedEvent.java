package ciamb.demo.springaxondemo.command.api.BLL.events.studentevent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDeletedEvent {
    private String studentId;
    private Integer id;

}
