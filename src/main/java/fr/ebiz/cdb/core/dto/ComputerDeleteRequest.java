package fr.ebiz.cdb.core.dto;

import java.util.List;

/**
 * Delete request.
 */
public class ComputerDeleteRequest {

    private List<String> selection;

    public List<String> getSelection() {
        return selection;
    }

    public void setSelection(List<String> selection) {
        this.selection = selection;
    }

}