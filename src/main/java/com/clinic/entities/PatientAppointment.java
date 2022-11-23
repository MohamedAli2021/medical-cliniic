package com.clinic.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "TBL_Medical_Appointments", schema = "CM")

public class PatientAppointment {
	@Id
	@Column(name = "id")
	private int id;
	private String patientName;
	private Date appointmentDate;
	private String cancellationReason;
	private String appointmentStatus;

}
