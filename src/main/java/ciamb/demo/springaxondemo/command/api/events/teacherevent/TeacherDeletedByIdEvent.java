package ciamb.demo.springaxondemo.command.api.events.teacherevent;

import ciamb.demo.springaxondemo.command.api.events.BaseEvent;
import lombok.Getter;

@Getter
public class TeacherDeletedByIdEvent extends BaseEvent<String> {
    private final Integer teacherId;
    public TeacherDeletedByIdEvent(String eventId, Integer teacherId) {
        super(eventId);
        this.teacherId = teacherId;
    }
}
