package anotaciones;

import java.sql.Date;
import java.sql.Time;
@Mock
public class Notificacion {
	
	private Long id;
	@MockStringAttribute({130230212,3232322})
	private Long idDevice;
	@MockStringAttribute({1,2})
	private Long idContenido;
	@MockStringAttribute({1,2,3})
	private Long idContexto;
	@MockStringAttribute({1,2,3})
	private Long idPaciente;
	@MockDateAttribute
	private Date date;
	@MockTimeAttribute
	private Time time;
	private Date dateReceived;
	private Time timeReceived;
	private boolean visto;

	public Notificacion() {
	}

	public Notificacion(Long id, Long idDevice, Long idContenido,
			Long idContexto, Long idPaciente, Date date, Time time,
			Date dateReceived, Time timeReceived, boolean visto) {
		super();
		this.id = id;
		this.idDevice = idDevice;
		this.idContenido = idContenido;
		this.idContexto = idContexto;
		this.idPaciente = idPaciente;
		this.date = date;
		this.time = time;
		this.dateReceived = dateReceived;
		this.timeReceived = timeReceived;
		this.visto = visto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdDevice() {
		return idDevice;
	}

	public void setIdDevice(Long idDevice) {
		this.idDevice = idDevice;
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

	public boolean isVisto() {
		return visto;
	}

	public void setVisto(boolean visto) {
		this.visto = visto;
	}
	
	public String toString(){
		return id+ " " +date+ " " +time;
	}

}
