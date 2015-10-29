package com.hermes.model;

import java.sql.Date;
import java.sql.Time;

public class Notificacion {

	private Long id;
	private Long idContenido;
	private Long idContexto;
	private Long idPaciente;
	private Date date;
	private Time time;
	private Date dateReceived;
	private Time timeReceived;
	private String deviceId; 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdContenido() {
		return idContenido;
	}

	public void setIdContenido(Long idContenido) {
		this.idContenido = idContenido;
	}

	public Long getIdContexto() {
		return idContexto;
	}

	public void setIdContexto(Long idContexto) {
		this.idContexto = idContexto;
	}

	public Long getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(Long idPaciente) {
		this.idPaciente = idPaciente;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Date getDateReceived() {
		return dateReceived;
	}

	public void setDateReceived(Date dateReceived) {
		this.dateReceived = dateReceived;
	}

	public Time getTimeReceived() {
		return timeReceived;
	}

	public void setTimeReceived(Time timeReceived) {
		this.timeReceived = timeReceived;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

}
