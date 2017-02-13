package fr.ebiz.cdb.model;

import java.util.Date;

public class Computer {

	private String name;
	private Date introduction;
	private Date discontinuation;
	private Manufacturer manufacturer;

	public Computer(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public Date getIntroduction() {
		return this.introduction;
	}

	public Date getDiscontinuation() {
		return this.discontinuation;
	}

	public Manufacturer getManufacturer() {
		return this.manufacturer;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIntroduction(Date introduction) {
		this.introduction = introduction;
	}

	public void setDiscontinuation(Date discontinuation) {
		this.discontinuation = discontinuation;
	}

	public void setManufaturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

}
