package ciamb.demo.springaxondemo.command.api.events;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseEvent<T> {
    private final T id;
}