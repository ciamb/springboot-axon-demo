package ciamb.demo.springaxondemo.core.api.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class EmptyNameExcpetion extends Exception{
    private String errorMessage;
    public EmptyNameExcpetion() {
        super();
        this.errorMessage = "Il campo nome deve essere vuoto!";
    }
}
