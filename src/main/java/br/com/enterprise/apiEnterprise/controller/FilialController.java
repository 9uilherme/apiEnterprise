package br.com.enterprise.apiEnterprise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.enterprise.apiEnterprise.model.Empresa;
import br.com.enterprise.apiEnterprise.model.Filial;
import br.com.enterprise.apiEnterprise.service.FilialService;

@Controller
public class FilialController {

	@Autowired
	FilialService filialService;
	
	@GetMapping(value="/filial/findAll")
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<List<Filial>>(filialService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping(value="/filial/save")
	public ResponseEntity<?> save(@RequestBody Filial filial){
		try {
			if(filial.getId() == null) {
				return new ResponseEntity<Filial>(filialService.save(filial), HttpStatus.OK);
			}else {
				return new ResponseEntity<Filial>(filialService.update(filial), HttpStatus.OK);
			}			
		}catch(NullPointerException e) {
			return new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping(value="/filial/delete")
	public ResponseEntity<Boolean> delete(@RequestBody Filial filial) {
		try {
			filialService.delete(filial);
			return new ResponseEntity<Boolean> (Boolean.TRUE, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Boolean> (Boolean.FALSE, HttpStatus.BAD_REQUEST);
		}
	}
	

	@GetMapping(value="/filial/findByStatus/{status}")
	public ResponseEntity<?> findByStatus(@PathVariable String status) {
		return new ResponseEntity<Filial>(filialService.findByStatus(status), HttpStatus.OK);
	}

	@GetMapping(value="/filial/findByMunicipio/{municipio}")
	public ResponseEntity<?> findByRazaoSocial(@PathVariable String municipio) {
		return new ResponseEntity<Filial>(filialService.findByMunicipio(municipio), HttpStatus.OK);
	}

	@GetMapping(value="/filial/findByCnpj/{cnpj}")
	public ResponseEntity<?> findByCnpj(@PathVariable String cnpj) {
		return new ResponseEntity<Filial>(filialService.findByCnpj(cnpj), HttpStatus.OK);
	}

	@GetMapping(value="/filial/findByUf/{uf}")
	public ResponseEntity<?> findByUf(@PathVariable String uf) {
		return new ResponseEntity<Filial>(filialService.findByCnpj(uf), HttpStatus.OK);
	}
	
	
}
