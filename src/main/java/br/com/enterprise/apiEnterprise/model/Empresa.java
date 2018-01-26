package br.com.enterprise.apiEnterprise.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Empresa {

	@Id
	@GeneratedValue
	private Long id;
	
	private String razaoSocial;
	
	private String cnpj;

	private String status;
	
	private Calendar dataCriacao;
	
	private Calendar dataAlteracao;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="empresa", cascade=CascadeType.ALL)
	private List<Filial> filiais;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Calendar getDataCriacao() {
		return dataCriacao;
	}

	@PrePersist
	public void setDataCriacao() {
		if(this.dataCriacao == null) {
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

	public List<Filial> getFiliais() {
		return filiais;
	}

	public void setFiliais(List<Filial> filiais) {
		this.filiais = filiais;
	}
	
	
	
}
