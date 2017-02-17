package fr.ebiz.cdb.model;

/**
 * Company bean.
 * Describes a company.
 */
public class Company {
	
	private int id;
	private String name;
	
	public Company() {

	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
