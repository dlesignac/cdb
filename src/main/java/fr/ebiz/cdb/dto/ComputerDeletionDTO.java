package fr.ebiz.cdb.dto;

import java.util.List;

/**
 * Delete request.
 */
public class ComputerDeletionDTO {

    private List<Integer> ids;

    /**
     * Constructor.
     *
     * @param ids ids
     */
    public ComputerDeletionDTO(List<Integer> ids) {
        this.ids = ids;
    }

    public List<Integer> getIds() {
        return ids;
    }

}
