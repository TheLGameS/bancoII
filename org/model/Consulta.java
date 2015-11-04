package org.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "consulta")
public class Consulta {
	
	@Id
	@Column(name="cod_consulta", unique=true, nullable=false)
	private int id = 0;
	
	@ManyToOne
	@JoinColumn(name = "cod_paciente")
	private Paciente paciente;

	@ManyToOne
	@JoinColumn(name = "cod_medico")
	private Medico medico;
	
	@Column(name = "data")
	private Date data;
	
	@Column(name = "valor")
	private double valor;
	
	public Consulta() {}
	
	public Consulta(int id, Paciente paciente, Medico medico, Date data, double valor) {
		this.setId(id);
		this.setPaciente(paciente);
		this.setMedico(medico);
		this.setData(data);
		this.setValor(valor);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	

}
