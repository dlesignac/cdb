package fr.ebiz.cdb.dto;

/**
 * Computer DTO.
 */
public class ComputerDTO {

    private String id;
    private String name;
    private String introduced;
    private String discontinued;
    private String companyId;

    /**
     * Constructor.
     *
     * @param builder builder
     */
    private ComputerDTO(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.introduced = builder.introduced;
        this.discontinued = builder.discontinued;
        this.companyId = builder.companyId;
    }

    /**
     * Builder.
     */
    public static class Builder {
        private String id;
        private String name;
        private String introduced;
        private String discontinued;
        private String companyId;

        /**
         * Constructor.
         *
         * @param name name
         */
        public Builder(String name) {
            this.name = name;
        }

        /**
         * Set id.
         *
         * @param id id
         * @return Builder
         */
        public Builder id(String id) {
            this.id = id;
            return this;
        }

        /**
         * Set introduced.
         *
         * @param introduced introduced
         * @return Builder
         */
        public Builder introduced(String introduced) {
            this.introduced = introduced;
            return this;
        }

        /**
         * Set discontinued.
         *
         * @param discontinued discontinued
         * @return Builder
         */
        public Builder discontinued(String discontinued) {
            this.discontinued = discontinued;
            return this;
        }

        /**
         * Set companyId.
         *
         * @param companyId companyId
         * @return Builder
         */
        public Builder companyId(String companyId) {
            this.companyId = companyId;
            return this;
        }

        /**
         * Builds ComputerDTO.
         *
         * @return ComputerDTO
         */
        public ComputerDTO build() {
            return new ComputerDTO(this);
        }
    }

    public String getId() {
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

    public String getCompanyId() {
        return companyId;
    }

    public void setId(String id) {
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

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

}
