package ciamb.demo.springaxondemo.configuration;

import ciamb.demo.springaxondemo.core.api.exception.StudentEventsErrorHandler;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventProcessingConfiguration {

    @Autowired
    public void configure(EventProcessingConfigurer eventProcessingConfigurer) {
        eventProcessingConfigurer.registerListenerInvocationErrorHandler(
                "student",
                configuration -> new StudentEventsErrorHandler()
        );
    }

}
