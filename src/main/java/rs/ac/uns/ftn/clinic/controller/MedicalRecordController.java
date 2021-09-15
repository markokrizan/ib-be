package rs.ac.uns.ftn.clinic.controller;

import rs.ac.uns.ftn.clinic.model.MedicalRecord;
import rs.ac.uns.ftn.clinic.payload.*;
import rs.ac.uns.ftn.clinic.service.MedicalRecordService;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/patients/{patientId}/records")
    @PreAuthorize("hasPermission(#patientId, 'MedicalRecord', 'read')")
    public MedicalRecord getPatientRecord(@PathVariable("patientId") Long patientId) {
        return medicalRecordService.getPatientRecord(patientId);
    }

    @PostMapping("/patients/{patientId}/records")
    @PreAuthorize("hasPermission(#medicalRecordRequest, 'MedicalRecord', 'write')")
    public MedicalRecord save(@Valid @RequestBody MedicalRecordRequest medicalRecordRequest) {
        MedicalRecord medicalRecord = modelMapper.map(medicalRecordRequest, MedicalRecord.class);

        return medicalRecordService.save(medicalRecord);
    }
}
