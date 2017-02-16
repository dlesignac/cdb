package fr.ebiz.cdb.model;

import java.time.LocalDate;

/**
 * Computer bean. Describes a computer.
 */
public class Computer {

	private int id;
	private String name;
	private LocalDate introduced;
	private LocalDate discontinued;
	private Company manufacturer;

	public Computer() {

	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public LocalDate getIntroduced() {
		return this.introduced;
	}

	public LocalDate getDiscontinued() {
		return this.discontinued;
	}

	public Company getManufacturer() {
		return this.manufacturer;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}

	public void setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
	}

	public void setManufacturer(Company manufacturer) {
		this.manufacturer = manufacturer;
	}

}
