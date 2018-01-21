package br.com.enterprise.apiEnterprise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.enterprise.apiEnterprise.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long>{

	public Empresa findByRazaoSocialContaining(String razaoSocial);
	
	public Empresa findByCnpj(String cnpj);
	
	public Empresa findByStatus(String status);
}
