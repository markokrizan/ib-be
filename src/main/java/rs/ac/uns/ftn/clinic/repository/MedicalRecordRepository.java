package rs.ac.uns.ftn.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.clinic.model.MedicalRecord;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    MedicalRecord findByPatientId(Long patientId);
}
