package com.clinic.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class PatientAppointmentDto {
	private int id;
	private String patientName;
	private Date appointmentDate;
	private String cancellationReason;
	
	
}
