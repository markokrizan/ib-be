package rs.ac.uns.ftn.clinic.payload;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.clinic.model.User;

@Getter
@Setter
@NoArgsConstructor
public class AppointmentRequest {

    @NotNull
    private User doctor;

    @NotBlank
    private User patient;

    @NotNull
    private Date date;
}
