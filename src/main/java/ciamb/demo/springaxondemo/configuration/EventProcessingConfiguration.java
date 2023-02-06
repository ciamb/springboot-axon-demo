package ciamb.demo.springaxondemo.configuration;

import ciamb.demo.springaxondemo.command.api.handler.StudentEventsErrorHandler;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventProcessingConfiguration {

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
