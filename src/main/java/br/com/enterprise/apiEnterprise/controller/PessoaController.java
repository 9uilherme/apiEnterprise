package br.com.enterprise.apiEnterprise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.enterprise.apiEnterprise.model.Pessoa;
import br.com.enterprise.apiEnterprise.service.PessoaService;

@Controller
public class PessoaController {

	@Autowired
	PessoaService pessoaService;
	
	@RequestMapping(method=RequestMethod.GET, value="/teste")
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<List<Pessoa>>(this.pessoaService.findAll(), HttpStatus.OK);
	}
	@RequestMapping(method=RequestMethod.POST, value="/add", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pessoa> save(@RequestBody Pessoa pessoa){
		this.pessoaService.save(pessoa);
		return new ResponseEntity<Pessoa>(HttpStatus.OK);
	}
	
	@GetMapping("/")
	public String getIndex() {
		return "index";
	}
}
