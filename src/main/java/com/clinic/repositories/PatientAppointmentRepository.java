package com.clinic.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinic.entities.PatientAppointment;

@Repository
public interface PatientAppointmentRepository extends JpaRepository<PatientAppointment, Integer> {
	List<PatientAppointment> findByAppointmentDateAndAppointmentStatus(Date appointmentDate, String statue);
	List<PatientAppointment> findByAppointmentDate(Date appointmentDate);
	List<PatientAppointment> findByPatientName(String name);
}
