package com.clinic.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.dto.PatientAppointmentDto;
import com.clinic.entities.PatientAppointment;
import com.clinic.services.AppointmentService;

@RestController
@RequestMapping(value = "/appointments")
public class PatientAppointmentsController {
	@Autowired
	AppointmentService ApopointmentService;

	@RequestMapping("/getAllTodayApointments")
	public ResponseEntity<List<PatientAppointmentDto>> getAllTodayAppointments() {

		return new ResponseEntity<>(ApopointmentService.getAllTodayPatientsAppintments(), HttpStatus.OK);

	}

	@PostMapping("/addAppointment")
	public ResponseEntity<PatientAppointment> addPAtientAppointment(@RequestBody PatientAppointmentDto dto) {
		PatientAppointment addPatientAppintment = ApopointmentService.addPatientAppintment(dto);
		return new ResponseEntity<>(addPatientAppintment, HttpStatus.OK);
	}

	@GetMapping("/getAllApointmentsByDate/{appointmentDate}")
	public ResponseEntity<List<PatientAppointmentDto>> getAllApointmentsByDate(@PathVariable Date appointmentDate) {

		return new ResponseEntity<>(ApopointmentService.getAllPatientsAppintmentsByDate(appointmentDate),
				HttpStatus.OK);
	}

	@GetMapping("/cancelAppointment/{appointmentName}")
	public ResponseEntity<List<PatientAppointmentDto>> getAllApointmentsByName(@PathVariable String appointmentName) {

		return new ResponseEntity<>(ApopointmentService.getAllPatientsAppintmentsByname(appointmentName),
				HttpStatus.OK);
	}

	@PostMapping("/cancelAppointment")
	public ResponseEntity<String> cancelPAtientAppointment(@RequestBody PatientAppointmentDto dto) {
		String cancelPatientAppintment = ApopointmentService.cancelPatientAppintment(dto);
		return new ResponseEntity<>(cancelPatientAppintment, HttpStatus.OK);
	}

}
