package ciamb.demo.springaxondemo.command.api.events.teacherevent;

import ciamb.demo.springaxondemo.command.api.events.BaseEvent;
import lombok.Getter;

@Getter
public class TeacherEditedEvent extends BaseEvent<String> {
    private final Integer teacherId;
    private final String name;
    private final String lastName;

    public TeacherEditedEvent(String id, Integer teacherId, String name, String lastName) {
        super(id);
        this.teacherId = teacherId;
        this.name = name;
        this.lastName = lastName;
    }
}