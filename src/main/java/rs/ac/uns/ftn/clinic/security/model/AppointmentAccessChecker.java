package rs.ac.uns.ftn.clinic.security.model;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rs.ac.uns.ftn.clinic.repository.AppointmentRepository;
import rs.ac.uns.ftn.clinic.repository.UserRepository;
import rs.ac.uns.ftn.clinic.model.Appointment;
import rs.ac.uns.ftn.clinic.model.User;
import rs.ac.uns.ftn.clinic.payload.AppointmentBookRequest;
import rs.ac.uns.ftn.clinic.payload.AppointmentRequest;

@Component
public class AppointmentAccessChecker extends ModelAccesChecker {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

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
        Appointment appointment = appointmentRepository.getOne(appointmentId);
        User user = userRepository.getOne(userId);

        return appointment.getDoctor().getId() == user.getId();
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
