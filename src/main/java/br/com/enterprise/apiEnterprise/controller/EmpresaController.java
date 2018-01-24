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
	
	@DeleteMapping(value="/empresa/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			Empresa empresaToDelete = empresaService.findById(id);
			empresaService.delete(empresaToDelete);
			return new ResponseEntity<Empresa> (empresaToDelete, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Boolean> (Boolean.FALSE, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value="/empresa/findById/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return new ResponseEntity<Empresa>(empresaService.findById(id), HttpStatus.OK);
	}

	@GetMapping(value="/empresa/findByStatus/{status}")
	public ResponseEntity<List<Empresa>> findByStatus(@PathVariable String status) {
		return new ResponseEntity<List<Empresa>>(empresaService.findByStatus(status), HttpStatus.OK);
	}

	@GetMapping(value="/empresa/findByRazaoSocial/{razaoSocial}")
	public ResponseEntity<List<Empresa>> findByRazaoSocial(@PathVariable String razaoSocial) {
		return new ResponseEntity<List<Empresa>>(empresaService.findByRazaoSocial(razaoSocial), HttpStatus.OK);
	}

	@GetMapping(value="/empresa/findByCnpj/{cnpj}")
	public  ResponseEntity<List<Empresa>> findByCnpj(@PathVariable String cnpj) {
		return new ResponseEntity<List<Empresa>> (empresaService.findByCnpj(cnpj), HttpStatus.OK);
	}
	
}
