package br.com.enterprise.apiEnterprise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.enterprise.apiEnterprise.model.Filial;
import br.com.enterprise.apiEnterprise.repository.filialRepository;

@Service
public class FilialService {

	@Autowired
	filialRepository filialRepository;
	
	public List<Filial> findAll(){
		return filialRepository.findAll();
		
	}
	
	public Filial save(Filial filial) {
		return filialRepository.save(filial);
	}
	
	public Filial update(Filial filial) {
		Filial filialSaved = filialRepository.findOne(filial.getId());
		
		montarFilial(filialSaved, filial);
		
		return filialRepository.save(filialSaved);
		
	}

	public void delete(Filial filial) {
		filialRepository.delete(filial);
	}
	
	private void montarFilial(Filial filialSaved, Filial filial) {
		filialSaved.setCnpj(filial.getCnpj());
		filialSaved.setStatus(filial.getStatus());
		filialSaved.setMunicipio(filial.getMunicipio());
		filialSaved.setUf(filial.getUf());
		filialSaved.setEmpresa(filial.getEmpresa());
	}

	public Filial findByStatus (String status) {
		return filialRepository.findByStatus(status);
	}
	
	public Filial findByCnpj (String cnpj) {
		return filialRepository.findByCnpj(cnpj);
	}

	public Filial findByMunicipio (String razaoSocial) {
		return filialRepository.findByMunicipio(razaoSocial);
	}
	
	public Filial findByUf (String razaoSocial) {
		return filialRepository.findByUf(razaoSocial);
	}
}
