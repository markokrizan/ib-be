package rs.ac.uns.ftn.clinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.clinic.model.MedicalRecord;
import rs.ac.uns.ftn.clinic.repository.MedicalRecordRepository;

@Service
public class MedicalRecordService {

    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    @Autowired
    UserService userService;

    public MedicalRecord getOne(Long id) {
        return medicalRecordRepository.getOne(id);
    }

    public MedicalRecord getPatientRecord(Long patientId) {
        return medicalRecordRepository.findByPatientId(patientId);
    }

    public MedicalRecord save(MedicalRecord medicalRecord) {
        MedicalRecord savedMedicalRecord = medicalRecordRepository.save(medicalRecord);

        // TODO: Hacky loading of related entites - find better solution
        if (savedMedicalRecord.getPatient() != null && savedMedicalRecord.getPatient().getId() != null) {
            savedMedicalRecord.setPatient(userService.getUserById(savedMedicalRecord.getPatient().getId()));
        }

        return savedMedicalRecord;
    }
}
