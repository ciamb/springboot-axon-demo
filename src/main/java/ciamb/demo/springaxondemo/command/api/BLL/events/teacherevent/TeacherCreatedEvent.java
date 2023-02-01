package ciamb.demo.springaxondemo.command.api.BLL.events.teacherevent;

import ciamb.demo.springaxondemo.command.api.BLL.events.BaseEvent;
import lombok.*;

import java.time.LocalDate;

@Getter
public class TeacherCreatedEvent extends BaseEvent<String> {

    public TeacherCreatedEvent(String eventId, String name, String lastName) {
        super(eventId);
        this.name = name;
        this.lastName = lastName;
    }
    private final String name;
    private final String lastName;

}
