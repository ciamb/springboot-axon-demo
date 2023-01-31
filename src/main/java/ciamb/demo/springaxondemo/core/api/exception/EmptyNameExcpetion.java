package ciamb.demo.springaxondemo.core.api.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
public class EmptyNameExcpetion extends Exception{

    private String errorMessage;

    public EmptyNameExcpetion(String errorMessage) {
        super();
        this.errorMessage = errorMessage;
    }
}
