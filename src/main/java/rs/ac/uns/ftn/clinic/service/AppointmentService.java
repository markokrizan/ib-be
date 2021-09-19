package rs.ac.uns.ftn.clinic.service;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.clinic.model.Appointment;
import rs.ac.uns.ftn.clinic.repository.AppointmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    public Page<Appointment> getAll(Pageable pageable) {
        return appointmentRepository.findAll(pageable);
    }

    public Page<Appointment> getDoctorAppointments(Long doctorId, Pageable pageable) {
        return appointmentRepository.findByDoctor_Id(doctorId, pageable);
    }

    public Appointment getOne(Long id) {
        return appointmentRepository.getOne(id);
    }

    public Appointment getLatestDoctorPatientAppointment(Long doctorId, Long patientId) {
        return appointmentRepository.findByDoctorIdAndPatientIdOrderByIdDesc(doctorId, patientId);
    }

    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public Appointment book(Long patientId, Long appointmentId) {
        appointmentRepository.bookAppointemnt(patientId, appointmentId);

        return getOne(appointmentId);
    }

    public void delete(Long id) {
        appointmentRepository.deleteById(id);
    }
}
