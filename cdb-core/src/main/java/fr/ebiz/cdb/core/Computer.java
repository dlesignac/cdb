package fr.ebiz.cdb.core;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class Computer {

    private Integer id;

    @NotNull
    @Size(min = 1, max = 255)
    private String name;
    private LocalDate introduced;
    private LocalDate discontinued;
    private Company manufacturer;

    public Integer getId() {
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

    public void setId(Integer id) {
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

    @Override
    public String toString() {
        return "Computer [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
                + ", manufacturer=" + manufacturer + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Computer other = (Computer) obj;

        return id == other.id;
    }

}