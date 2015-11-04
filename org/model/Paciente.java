package org.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
//import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "paciente")
public class Paciente {
	
	@Id
    @SequenceGenerator(name="pk_sequence",sequenceName="seq_paciente_pk", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence")
	@Column(name="cod_paciente", unique=true, nullable=false)
	private Integer id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "cidade")
	private String cidade;

	@Column(name = "uf")
	private String uf;
	
	@Column(name = "endereco")
	private String endereco;
	
	@Column(name = "data_nasc")
	private Date dataNasc;
	
	public Paciente() {}
	
	public Paciente(int id, String nome, String cidade, String uf, String endereco, Date dataNasc) {
		this.setId(id);
		this.setNome(nome);
		this.setCidade(cidade);
		this.setUf(uf);
		this.setEndereco(endereco);
		this.setDataNasc(dataNasc);
	}

	public Paciente(String nome, String cidade, String uf, String endereco, Date dataNasc) {
		this.setNome(nome);
		this.setCidade(cidade);
		this.setUf(uf);
		this.setEndereco(endereco);
		this.setDataNasc(dataNasc);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}
	
	
	
}
