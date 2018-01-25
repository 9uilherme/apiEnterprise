package br.com.enterprise.apiEnterprise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.enterprise.apiEnterprise.model.Filial;

public interface filialRepository extends JpaRepository<Filial, Long>{

	public Filial findByCnpj(String cnpj);
	
	public Filial findByStatus(String status);

	public Filial findByMunicipio(String municipio);
	
	public Filial findByUf(String uf);
	
	public List<Filial> findAll();
}
