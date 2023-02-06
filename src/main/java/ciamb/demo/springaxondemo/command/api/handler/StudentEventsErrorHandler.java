package ciamb.demo.springaxondemo.command.api.handler;

import lombok.extern.log4j.Log4j2;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.EventMessageHandler;
import org.axonframework.eventhandling.ListenerInvocationErrorHandler;

import javax.annotation.Nonnull;
import javax.persistence.EntityNotFoundException;

/*
 * Questa classe permette di gestire gli errori che vengono lanciati nei metodi dell' handler
 * che viene associato nella configurazione, in questo caso "student".
  */
@Log4j2
public class StudentEventsErrorHandler implements ListenerInvocationErrorHandler {

    @Override
    public void onError(@Nonnull Exception exception, @Nonnull EventMessage<?> eventMessage, @Nonnull EventMessageHandler eventMessageHandler) {
        if(exception instanceof EntityNotFoundException) {
            log.error("L'utente con id: " + eventMessage.getPayload() + " non è stato trovato nel db!");
        } else if(exception instanceof NullPointerException) {
            log.error("Hai provato ad inserire un valore null, per favore controlla i dati.");
        } else {
            log.error(exception);
        }
    }
}
