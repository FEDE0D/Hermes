package com.hermes.model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Notificacion {

	private Long id;
	private String idTablet;
	private Long idContenido;
	private Long idContexto;
	private Long idPaciente;
	private Timestamp timestamp;
	private Date dateReceived;
	private Time timeReceived;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getIdTablet() {
		return idTablet;
	}

	public void setIdTablet(String idTablet) {
		this.idTablet = idTablet;
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

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

}
