package br.com.enterprise.apiEnterprise.model;

public class XmlViewHelper {

	private String path;
	private Boolean itWorked;
	
	public XmlViewHelper(String path){
		this.path = path;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Boolean getItWorked() {
		return itWorked;
	}
	public void setItWorked(Boolean itWorked) {
		this.itWorked = itWorked;
	}
	
	
}
