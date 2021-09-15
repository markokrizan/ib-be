package rs.ac.uns.ftn.clinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.clinic.model.MedicalRecord;
import rs.ac.uns.ftn.clinic.repository.MedicalRecordRepository;

@Service
public class MedicalRecordService {

    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    public MedicalRecord getOne(Long id) {
        return medicalRecordRepository.getOne(id);
    }

    public MedicalRecord getPatientRecord(Long patientId) {
        return medicalRecordRepository.findByPatientId(patientId);
    }

    public MedicalRecord save(MedicalRecord medicalRecord) {
        return medicalRecordRepository.save(medicalRecord);
    }
}
