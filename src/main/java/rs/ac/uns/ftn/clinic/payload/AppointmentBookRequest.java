package rs.ac.uns.ftn.clinic.payload;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.clinic.model.Appointment;
import rs.ac.uns.ftn.clinic.model.User;

@Getter
@Setter
@NoArgsConstructor
public class AppointmentBookRequest implements Serializable {

    @NotNull
    private Appointment appointment;

    @NotNull
    private User patient;
}
