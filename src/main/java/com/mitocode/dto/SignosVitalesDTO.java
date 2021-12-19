package com.mitocode.dto;

import java.time.LocalDateTime;

public class SignosVitalesDTO {

	private Integer idSignoVital;
	private PacienteDTO paciente;
	private LocalDateTime fecha;
	private String temperatura;
	private String pulso;
	private String ritmoRespiratorio;
	public Integer getIdSignoVital() {
		return idSignoVital;
	}
	public void setIdSignoVital(Integer idSignoVital) {
		this.idSignoVital = idSignoVital;
	}
	public PacienteDTO getPaciente() {
		return paciente;
	}
	public void setPaciente(PacienteDTO paciente) {
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
