package ciamb.demo.springaxondemo.command.api.BLL.handler;

import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.EventMessageHandler;
import org.axonframework.eventhandling.ListenerInvocationErrorHandler;

import javax.annotation.Nonnull;
import javax.persistence.EntityNotFoundException;

public class StudentEventsErrorHandler implements ListenerInvocationErrorHandler {

    @Override
    public void onError(@Nonnull Exception exception, @Nonnull EventMessage<?> eventMessage, @Nonnull EventMessageHandler eventMessageHandler) {
        if(exception instanceof EntityNotFoundException) {
            System.out.println("L'utente con id: " + eventMessage.getPayload() + " non è stato trovato nel db!");
        } else {
            System.out.println("Errore generico");
        }
    }
}
