package rs.ac.uns.ftn.clinic.security.access;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rs.ac.uns.ftn.clinic.service.AppointmentService;
import rs.ac.uns.ftn.clinic.model.Appointment;
import rs.ac.uns.ftn.clinic.payload.AppointmentBookRequest;
import rs.ac.uns.ftn.clinic.payload.AppointmentRequest;

@Component
public class AppointmentAccessChecker extends ModelAccesChecker {

    @Autowired
    AppointmentService appointmentService;

    @Override
    public boolean canAccess(Serializable payload, Long userId, Object permission) {
        switch (permission.toString()) {
            case "read":
                return canRead(payload, userId);
            case "write":
                return canWrite(payload, userId);
            case "appointment-book":
                return canBook(payload, userId);
            default:
                return false;
        }
    }

    private boolean canRead(Serializable payload, Long userId) {
        Long appointmentId = (Long) payload;
        Appointment appointment = appointmentService.getOne(appointmentId);

        return appointment.getDoctor().getId() == userId;
    }

    private boolean canWrite(Serializable payload, Long userId) {
        AppointmentRequest requestData = (AppointmentRequest) payload;

        return requestData.getDoctor().getId() == userId;
    }

    private boolean canBook(Serializable payload, Long userId) {
        AppointmentBookRequest requestData = (AppointmentBookRequest) payload;

        return requestData.getPatient().getId() == userId;
    }

    @Override
    public AccessCheckerType getType() {
        return AccessCheckerType.APPOINTMENT;
    }
}
