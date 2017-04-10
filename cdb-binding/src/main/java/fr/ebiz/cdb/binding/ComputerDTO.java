package fr.ebiz.cdb.binding;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ComputerDTO {

    private Integer id;

    @NotNull
    @Size(min = 1, max = 255)
    private String name;

    @UTCDate
    private String introduced;

    @UTCDate
    private String discontinued;

    private Integer companyId;

    private String companyName;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIntroduced() {
        return introduced;
    }

    public String getDiscontinued() {
        return discontinued;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIntroduced(String introduced) {
        this.introduced = introduced;
    }

    public void setDiscontinued(String discontinued) {
        this.discontinued = discontinued;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}