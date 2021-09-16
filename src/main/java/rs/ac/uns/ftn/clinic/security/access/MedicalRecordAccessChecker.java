package rs.ac.uns.ftn.clinic.security.access;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rs.ac.uns.ftn.clinic.service.AppointmentService;
import rs.ac.uns.ftn.clinic.model.Appointment;
import rs.ac.uns.ftn.clinic.payload.MedicalRecordRequest;

@Component
public class MedicalRecordAccessChecker extends ModelAccesChecker {

    @Autowired
    AppointmentService appointmentService;

    @Override
    public boolean canAccess(Serializable payload, Long userId, Object permission) {
        switch (permission.toString()) {
            case "read":
                return canRead(payload, userId);
            case "write":
                return canWrite(payload, userId);
            default:
                return false;
        }
    }

    private boolean canRead(Serializable payload, Long userId) {
        Long patientId = (Long) payload;

        return appointmentService.getLatestDoctorPatientAppointment(userId, patientId) != null;
    }

    private boolean canWrite(Serializable payload, Long userId) {
        MedicalRecordRequest requestData = (MedicalRecordRequest) payload;

        Appointment latestAppointment = appointmentService.getLatestDoctorPatientAppointment(userId,
                requestData.getPatient().getId());

        if (latestAppointment == null) {
            return false;
        }

        return DateUtils.isSameDay(new Date(), latestAppointment.getDate());
    }

    @Override
    public AccessCheckerType getType() {
        return AccessCheckerType.MEDICALRECORD;
    }
}
