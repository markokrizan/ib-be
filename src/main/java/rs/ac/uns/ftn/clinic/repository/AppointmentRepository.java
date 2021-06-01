package rs.ac.uns.ftn.clinic.repository;

import rs.ac.uns.ftn.clinic.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}
