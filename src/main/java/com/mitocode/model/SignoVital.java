package com.mitocode.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "signo_vital")
public class SignoVital {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSignoVital;

	@ManyToOne
	@JoinColumn(name = "id_paciente", nullable = false, foreignKey = @ForeignKey(name = "FK_signo_vital_paciente"))
	private Paciente paciente;

	@Column(name = "fecha", nullable = false)
	private LocalDateTime fecha;

	@Size(min = 2, message = "{temperatura.size}")
	@Column(name = "temperatura", nullable = false, length = 20)
	private String temperatura;

	@Size(min = 2, message = "El pulso debe tener mínimo 3 caracteres")
	@Column(name = "pulso", nullable = false, length = 20)
	private String pulso;

	@Size(min = 2, message = "El ritmo respiratorio debe tener minimo 3 caracteres")
	@Column(name = "ritmo_respiratorio", nullable = false, length = 20)
	private String ritmoRespiratorio;

	public Integer getIdSignoVital() {
		return idSignoVital;
	}

	public void setIdSignoVital(Integer idSignoVital) {
		this.idSignoVital = idSignoVital;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public String getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(String temperatura) {
		this.temperatura = temperatura;
	}

	public String getPulso() {
		return pulso;
	}

	public void setPulso(String pulso) {
		this.pulso = pulso;
	}

	public String getRitmoRespiratorio() {
		return ritmoRespiratorio;
	}

	public void setRitmoRespiratorio(String ritmoRespiratorio) {
		this.ritmoRespiratorio = ritmoRespiratorio;
	}

}
