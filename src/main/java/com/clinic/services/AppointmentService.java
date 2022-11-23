package com.clinic.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.dto.PatientAppointmentDto;
import com.clinic.entities.PatientAppointment;
import com.clinic.repositories.PatientAppointmentRepository;

@Service
public class AppointmentService {
	@Autowired
	PatientAppointmentRepository patientAppointmentRepository;

	public List<PatientAppointmentDto> getAllTodayPatientsAppintments() {
		List<PatientAppointmentDto> allTodayAppointments = new ArrayList<>();
		List<PatientAppointment> findAllTodayAppointments = patientAppointmentRepository
				.findByAppointmentDateAndAppointmentStatus(new Date(), "Active");
		for (PatientAppointment patientAppointment : findAllTodayAppointments) {
			PatientAppointmentDto patientAppointmentDto = new PatientAppointmentDto();
			patientAppointmentDto.setId(patientAppointment.getId());
			patientAppointmentDto.setPatientName(patientAppointmentDto.getPatientName());
			allTodayAppointments.add(patientAppointmentDto);
		}
		return allTodayAppointments;
	}

	public PatientAppointment addPatientAppintment(PatientAppointmentDto patientAppointmentDto) {
		PatientAppointment patientAppointment = new PatientAppointment();
		patientAppointment.setPatientName(patientAppointmentDto.getPatientName());
		patientAppointment.setAppointmentDate(patientAppointmentDto.getAppointmentDate());
		patientAppointment.setAppointmentStatus("Active");
		return patientAppointmentRepository.save(patientAppointment);
	}

	public String cancelPatientAppintment(PatientAppointmentDto patientAppointmentDto) {
		try {
			Optional<PatientAppointment> findById = patientAppointmentRepository
					.findById(patientAppointmentDto.getId());
			if (findById.isPresent()) {
				PatientAppointment patientAppointment = findById.get();
				patientAppointment.setAppointmentStatus("Cancelled");
				patientAppointment.setCancellationReason(patientAppointmentDto.getCancellationReason());
				patientAppointmentRepository.save(patientAppointment);
				return "Done Successfully";
			}
			return "";
		} catch (Exception e) {
			return "Cancellation issue" + e;
		}

	}

	public List<PatientAppointmentDto> getAllPatientsAppintmentsByDate(Date appointmentDate) {
		List<PatientAppointmentDto> allAppointmentsByDate = new ArrayList<>();
		List<PatientAppointment> findAllByAppointmentDate = patientAppointmentRepository
				.findByAppointmentDate(appointmentDate);
		for (PatientAppointment patientAppointment : findAllByAppointmentDate) {
			PatientAppointmentDto patientAppointmentDto = new PatientAppointmentDto();
			patientAppointmentDto.setId(patientAppointment.getId());
			patientAppointmentDto.setPatientName(patientAppointmentDto.getPatientName());
			allAppointmentsByDate.add(patientAppointmentDto);
		}
		return allAppointmentsByDate;
	}

	public List<PatientAppointmentDto> getAllPatientsAppintmentsByname(String patientName) {
		List<PatientAppointmentDto> allAppointmentsByName = new ArrayList<>();
		List<PatientAppointment> findAllByName = patientAppointmentRepository.findByPatientName(patientName);
		for (PatientAppointment patientAppointment : findAllByName) {
			PatientAppointmentDto patientAppointmentDto = new PatientAppointmentDto();
			patientAppointmentDto.setId(patientAppointment.getId());
			patientAppointmentDto.setPatientName(patientAppointmentDto.getPatientName());
			allAppointmentsByName.add(patientAppointmentDto);
		}
		return allAppointmentsByName;
	}

}
