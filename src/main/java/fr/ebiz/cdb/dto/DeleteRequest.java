package fr.ebiz.cdb.dto;

import java.util.List;

/**
 * Delete request.
 */
public class DeleteRequest {

    private List<Integer> ids;

    /**
     * Constructor.
     *
     * @param ids ids
     */
    public DeleteRequest(List<Integer> ids) {
        this.ids = ids;
    }

    public List<Integer> getIds() {
        return ids;
    }

}
