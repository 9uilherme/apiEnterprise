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
import br.com.enterprise.apiEnterprise.service.EmpresaService;

@Controller
public class EmpresaController {

	@Autowired
	EmpresaService empresaService;
	
	@GetMapping(value="/empresa/findAll")
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<List<Empresa>>(empresaService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping(value="/empresa/save")
	public ResponseEntity<?> save(@RequestBody Empresa empresa){
		try {
			if(empresa.getId() == null) {
				return new ResponseEntity<Empresa>(empresaService.save(empresa), HttpStatus.OK);
			}else {
				return new ResponseEntity<Empresa>(empresaService.update(empresa), HttpStatus.OK);
			}			
		}catch(NullPointerException e) {
			return new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping(value="/empresa/delete")
	public ResponseEntity<Boolean> delete(@RequestBody Empresa empresa) {
		try {
			empresaService.delete(empresa);
			return new ResponseEntity<Boolean> (Boolean.TRUE, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Boolean> (Boolean.FALSE, HttpStatus.BAD_REQUEST);
		}
	}


	@GetMapping(value="/empresa/findByStatus/{status}")
	public ResponseEntity<?> findByStatus(@PathVariable String status) {
		return new ResponseEntity<Empresa>(empresaService.findByStatus(status), HttpStatus.OK);
	}

	@GetMapping(value="/empresa/findByRazaoSocial/{razaoSocial}")
	public ResponseEntity<?> findByRazaoSocial(@PathVariable String razaoSocial) {
		return new ResponseEntity<Empresa>(empresaService.findByRazaoSocial(razaoSocial), HttpStatus.OK);
	}

	@GetMapping(value="/empresa/findByCnpj/{cnpj}")
	public ResponseEntity<?> findByCnpj(@PathVariable String cnpj) {
		return new ResponseEntity<Empresa>(empresaService.findByCnpj(cnpj), HttpStatus.OK);
	}
	
}
