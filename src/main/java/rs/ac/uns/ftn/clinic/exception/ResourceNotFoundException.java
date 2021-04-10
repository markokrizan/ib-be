package rs.ac.uns.ftn.clinic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 3884947098514625542L;

    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
