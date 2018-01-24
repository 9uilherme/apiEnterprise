package br.com.enterprise.apiEnterprise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.enterprise.apiEnterprise.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long>{

	public List<Empresa> findByRazaoSocialContainingIgnoreCase(String razaoSocial);
	
	public List<Empresa> findByCnpjContaining(String cnpj);

	public List<Empresa> findByStatusContainingIgnoreCase(String status);
	
	public Empresa findById(Long id);
	
	public void delete(Long id);
}
