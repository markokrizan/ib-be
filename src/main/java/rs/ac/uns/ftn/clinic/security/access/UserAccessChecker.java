package rs.ac.uns.ftn.clinic.security.access;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rs.ac.uns.ftn.clinic.model.Appointment;
import rs.ac.uns.ftn.clinic.model.User;
import rs.ac.uns.ftn.clinic.service.AppointmentService;
import rs.ac.uns.ftn.clinic.service.UserService;

@Component
public class UserAccessChecker extends ModelAccesChecker {

    @Autowired
    UserService userService;

    @Autowired
    AppointmentService appointmentService;

    @Override
    public boolean canAccess(Serializable payload, Long userId, Object permission) {
        switch (permission.toString()) {
            case "read":
                return canRead(payload, userId);
            default:
                return false;
        }
    }

    private boolean canRead(Serializable payload, Long userId) {
        Long requestedUserId = (Long) payload;

        User requestedUser = userService.getUserById(requestedUserId);
        Boolean isDoctor = hasRole(requestedUser, "ROLE_DOCTOR");

        if (isDoctor) {
            return true;
        }

        Appointment appointment = appointmentService.getLatestDoctorPatientAppointment(userId, requestedUserId);

        if (appointment != null) {
            return true;
        }

        return requestedUserId == userId;
    }

    @Override
    protected AccessCheckerType getType() {
        return AccessCheckerType.USER;
    }
}
