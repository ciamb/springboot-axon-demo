package ciamb.demo.springaxondemo.command.api.BLL.events;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseEvent<T> {

    private final T eventId;

}
