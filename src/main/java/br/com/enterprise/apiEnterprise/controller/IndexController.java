package br.com.enterprise.apiEnterprise.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.thoughtworks.xstream.XStream;

import br.com.enterprise.apiEnterprise.model.Empresa;
import br.com.enterprise.apiEnterprise.model.Filial;
import br.com.enterprise.apiEnterprise.service.EmpresaService;

@Controller
public class IndexController {

	@Autowired
	EmpresaService empresaService;

	@GetMapping("/")
	public String getIndex() {
		return "index";
	}
	@GetMapping("/empresaEdit")
	public String getEmpresa() {
		return "index";
	}
	@GetMapping("/empresaList")
	public String getEmpresaList() {
		return "index";
	}

	@GetMapping("/importacao")
	public String getImportacao() {
		return "index";
	}

	@GetMapping("/empresaEdit/{id}")
	public String getEmpresaId(@PathVariable Long id) {
		return "index";
	}

	@GetMapping("/allToXml")
	public ResponseEntity<?> allToXml(){
		try {
			XStream x = new XStream();
			generateXmlEmpresa(x);

			List<Empresa> empresa = empresaService.findAll();

			int number =  new Random().nextInt(10000000);
			String path = "./src/main/resources/static/xml/" + number + ".xml";

			FileWriter w = new FileWriter(path);
			writeToXml(w,x,empresa);
			
			return new ResponseEntity<Integer>(number, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.BAD_REQUEST);
		}

	}
	@PostMapping("/xmlToObj")
	public ResponseEntity<?> xmlToObj (@RequestBody List<Empresa> empresa){
		List<Empresa> fail = new ArrayList<Empresa>();
		for (Empresa empresa2 : empresa) {
			try {
				if(empresa2.getId() == null)
				empresaService.save(empresa2);
				else {
					empresaService.update(empresa2);
				}
			}catch(Exception e) {
				fail.add(empresa2);
				continue;
			}
		}
		if(fail.size() > 0) {
			return new ResponseEntity<List<Empresa>>(fail, HttpStatus.PARTIAL_CONTENT);
		}else {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
	}
	 
	private void writeToXml(FileWriter w,XStream x, Object obj) {
		try {
			w.write(x.toXML(obj));
			w.flush();
			w.close();
		}catch(IOException e) {
			System.out.println("--- writeToXml failed! ---");
		}

	}
	private void generateXmlEmpresa(XStream x) {
		x.alias("empresa", Empresa.class);
		x.alias("filial", Filial.class);

		x.addImplicitCollection(Empresa.class, "filiais");
	}

	@GetMapping("/oneToXml/{idEmpresa}")
	public ResponseEntity<?> oneToXml(@PathVariable Long idEmpresa){
		try {
			XStream x = new XStream();
			generateXmlEmpresa(x);

			Empresa empresa = empresaService.findById(idEmpresa);
			int number =  new Random().nextInt(10000000);
			String path = "./src/main/resources/static/xml/" + number + ".xml";

			FileWriter w = new FileWriter(path);
			writeToXml(w,x,empresa);
			
			return new ResponseEntity<Integer>(number, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.BAD_REQUEST);
		}

	}

	
}

