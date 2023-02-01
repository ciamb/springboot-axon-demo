package ciamb.demo.springaxondemo.command.api.BLL.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class BaseCommand<T> {

    public BaseCommand(T id) {
        this.id = id;
    }

    // Devo specificare il TargetAggregateIdentifier tramite apposita annotazione, in modo da dare un identificatore
    // univoco all'evento che stiamo per creare
    @TargetAggregateIdentifier
    private final T id;

    public T getId() {
        return id;
    }

}