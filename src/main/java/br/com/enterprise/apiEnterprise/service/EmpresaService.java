package br.com.enterprise.apiEnterprise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.enterprise.apiEnterprise.model.Empresa;
import br.com.enterprise.apiEnterprise.model.Filial;
import br.com.enterprise.apiEnterprise.repository.EmpresaRepository;

@Service
public class EmpresaService {
	
	@Autowired
	EmpresaRepository empresaRepository;
	
	public List<Empresa> findAll(){
		return empresaRepository.findAll();
		
	}
	
	public Empresa save(Empresa empresa) {
		setarEmpresaEmFiliais(empresa);
		return empresaRepository.save(empresa);
		
	}
	
	private void setarEmpresaEmFiliais(Empresa empresa) {
		if(empresa.getFiliais().size() > 0) {
			for (Filial filial : empresa.getFiliais()) {
				filial.setEmpresa(empresa);
			}
		}
	}
	
	public Empresa update(Empresa empresa) {
		Empresa empresaSaved = empresaRepository.findOne(empresa.getId());
		
		montarEmpresa(empresaSaved, empresa);
		
		return empresaRepository.save(empresaSaved);
		
	}

	public void delete(Empresa empresa) {
		empresaRepository.delete(empresa.getId());
	}
	
	private void montarEmpresa(Empresa empresaSaved, Empresa empresa) {
		empresaSaved.setCnpj(empresa.getCnpj());
		empresaSaved.setRazaoSocial(empresa.getRazaoSocial());
		empresaSaved.setFiliais(empresa.getFiliais());
		setarEmpresaEmFiliais(empresaSaved);
		empresaSaved.setStatus(empresa.getStatus());
	}

	public Empresa findById (Long id) {
		return empresaRepository.findById(id);
	}

	public List<Empresa> findByStatus (String status) {
		return (List<Empresa>) empresaRepository.findByStatusContainingIgnoreCase(status);
	}
	
	public List<Empresa> findByCnpj (String cnpj) {
		return (List<Empresa>) empresaRepository.findByCnpjContaining(cnpj);
	}
	
	public List<Empresa> findByRazaoSocial (String razaoSocial) {
		return  (List<Empresa>) empresaRepository.findByRazaoSocialContainingIgnoreCase(razaoSocial);
	}
}
