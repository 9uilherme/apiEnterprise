package br.com.enterprise.apiEnterprise.model;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Entity
public class Filial {

	@Id
	@GeneratedValue
	private Long id;
	
	private String cnpj;
	
	private String municipio;
	
	private String uf;
	
	private Calendar dataCriacao;
	
	private Calendar dataAlteracao;
	
	private String status;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Empresa empresa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Calendar getDataCriacao() {
		return dataCriacao;
	}

	@PrePersist
	public void setDataCriacao() {
		if(dataCriacao == null) {
			this.dataCriacao = Calendar.getInstance();
		}
	}

	public Calendar getDataAlteracao() {
		return dataAlteracao;
	}

	@PreUpdate
	public void setDataAlteracao() {
		this.dataAlteracao = Calendar.getInstance();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	
}
