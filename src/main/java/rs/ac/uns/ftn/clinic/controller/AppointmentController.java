package rs.ac.uns.ftn.clinic.controller;

import rs.ac.uns.ftn.clinic.model.Appointment;
import rs.ac.uns.ftn.clinic.payload.*;
import rs.ac.uns.ftn.clinic.service.AppointmentService;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/appoinments")
    @PreAuthorize("isAuthenticated()")
    public Page<Appointment> getAll(Pageable pageable) {
        return appointmentService.getAll(pageable);
    }

    @GetMapping("/doctors/{doctorId}/appointments")
    @PreAuthorize("isAuthenticated()")
    public Page<Appointment> getDoctorAppointments(@PathVariable("doctorId") Long doctorId, Pageable pageable) {
        return appointmentService.getDoctorAppointments(doctorId, pageable);
    }

    @PreAuthorize("hasRole('ADMIN') || hasPermission(#appoinmentId, 'Appointment', 'read')")
    @GetMapping("/appoinments/{appoinmentId}")
    public Appointment getOne(@PathVariable("appoinmentId") Long appoinmentId) {
        return appointmentService.getOne(appoinmentId);
    }

    @PreAuthorize("hasRole('ADMIN') || hasPermission(#appointmentRequest, 'Appointment', 'write')")
    @PostMapping("/appoinments")
    public Appointment save(@Valid @RequestBody AppointmentRequest appointmentRequest) {
        Appointment appointment = modelMapper.map(appointmentRequest, Appointment.class);

        return appointmentService.save(appointment);
    }

    @PreAuthorize("hasRole('ADMIN') || hasPermission(#appointmentBookRequest, 'Appointment', 'appointment-book')")
    @PutMapping("/appoinments/book")
    public Appointment book(@Valid @RequestBody AppointmentBookRequest appointmentBookRequest) {
        return appointmentService.book(appointmentBookRequest.getPatient().getId(),
                appointmentBookRequest.getAppointment().getId());
    }

    @DeleteMapping("/appoinments/{appoinmentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable("appoinmentId") Long appoinmentId) {
        appointmentService.delete(appoinmentId);
    }
}
