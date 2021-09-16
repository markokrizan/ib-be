package rs.ac.uns.ftn.clinic.model;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.clinic.security.crypto.AttributeEncryptor;

@Entity
@Table(name = "medical_records")
@Getter
@Setter
@NoArgsConstructor
public class MedicalRecord extends BaseModel {
    @OneToOne
    @JoinColumn(name = "patient_id", nullable = true)
    private User patient;

    @Convert(converter = AttributeEncryptor.class)
    private String content;
}
