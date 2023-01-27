package ciamb.demo.springaxondemo.configuration;

import ciamb.demo.springaxondemo.command.api.BLL.handler.StudentEventsErrorHandler;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.ErrorHandler;
import org.axonframework.eventhandling.TrackingEventProcessor;
import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventProcessingConfiguration {

    // Autowired è commentato perché blocca l'applicativo se viene generato un errore che dovrebbe gestire
    // mentre al momento senza autowired lancia l'eccezione e poi passa al listener successivo

    // DA CAPIRE SE FUNZIONA/SERVE
    @Autowired
    public void configure(final EventProcessingConfigurer eventProcessingConfigurer) {
        eventProcessingConfigurer.registerListenerInvocationErrorHandler(
                "student",
                configuration -> new StudentEventsErrorHandler()
                // ogni volta che viene lanciata un'eccezione all'interno della classe annotata con
                // il @ProcessingGroup("student") la classe students handler dovrebbe gestire l'eccezione
        );
    }

}
