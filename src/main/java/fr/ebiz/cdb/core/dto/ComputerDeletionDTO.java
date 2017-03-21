package fr.ebiz.cdb.core.dto;

import java.util.List;

/**
 * Delete request.
 */
public class ComputerDeletionDTO {

    private List<String> ids;

    /**
     * Constructor.
     *
     * @param ids ids
     */
    public ComputerDeletionDTO(List<String> ids) {
        this.ids = ids;
    }

    public List<String> getIds() {
        return ids;
    }

}