package rs.ac.uns.ftn.clinic.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
public class Appointment extends BaseModel {
    @OneToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private User doctor;

    @OneToOne
    @JoinColumn(name = "patient_id", nullable = true)
    private User patient;

    private Date date;
}
