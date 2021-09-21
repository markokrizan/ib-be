package rs.ac.uns.ftn.clinic.payload;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.clinic.model.User;

@Getter
@Setter
@NoArgsConstructor
public class MedicalRecordRequest implements Serializable {
    @Nullable
    private Long id;

    @NotNull
    private User patient;

    @NotBlank
    private String content;
}
