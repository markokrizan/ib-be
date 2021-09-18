package rs.ac.uns.ftn.clinic.repository;

import rs.ac.uns.ftn.clinic.model.Appointment;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Appointment SET patient_id = :patient where id = :id")
    int bookAppointemnt(@Param(value = "patient") Long patientId, @Param(value = "id") long appointmentId);

    Appointment findByDoctorIdAndPatientIdOrderByIdDesc(Long doctorId, Long patientId);

    Page<Appointment> findByDoctor_Id(Long doctorId, Pageable pageable);
}
