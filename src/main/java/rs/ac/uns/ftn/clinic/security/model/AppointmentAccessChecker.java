package rs.ac.uns.ftn.clinic.security.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rs.ac.uns.ftn.clinic.repository.AppointmentRepository;
import rs.ac.uns.ftn.clinic.repository.UserRepository;
import rs.ac.uns.ftn.clinic.model.Appointment;
import rs.ac.uns.ftn.clinic.model.User;

@Component
public class AppointmentAccessChecker implements ModelAccesChecker {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Override
    public boolean canAccess(Long modelId, Long userId, Object permission) {
        User user = userRepository.getOne(userId);
        Appointment appointment = appointmentRepository.getOne(modelId);

        return appointment.getDoctor().getId() == user.getId();
    }

    @Override
    public AccessCheckerType getType() {
        return AccessCheckerType.APPOINTMENT;
    }
}
