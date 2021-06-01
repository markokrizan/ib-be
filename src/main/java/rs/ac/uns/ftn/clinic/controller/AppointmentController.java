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
    @PreAuthorize("hasAuthority('READ_ALL_APPOINTMENTS')")
    public Page<Appointment> getAll(Pageable pageable) {
        return appointmentService.getAll(pageable);
    }

    // TODO: Security
    @GetMapping("/appoinments/{appoinmentId}")
    public Appointment getOne(@PathVariable("appoinmentId") Long appoinmentId) {
        return appointmentService.getOne(appoinmentId);
    }

    // TODO: Security
    @PostMapping("/appoinments")
    public Appointment save(@Valid @RequestBody AppointmentRequest appointmentRequest) {
        Appointment appointment = modelMapper.map(appointmentRequest, Appointment.class);

        return appointmentService.save(appointment);
    }

    // TODO: Security
    @DeleteMapping("/appoinments/{appoinmentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable("appoinmentId") Long appoinmentId) {
        appointmentService.delete(appoinmentId);
    }
}
