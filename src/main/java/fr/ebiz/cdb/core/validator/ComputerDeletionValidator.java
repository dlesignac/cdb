package fr.ebiz.cdb.core.validator;

import fr.ebiz.cdb.core.dto.ComputerDeletionDTO;

import java.util.ArrayList;
import java.util.List;

public class ComputerDeletionValidator {

    /**
     * Validates computer deletion.
     *
     * @param deletionRequest deletionRequest
     * @return errors
     */
    public static List<String> validate(ComputerDeletionDTO deletionRequest) {
        List<String> errors = new ArrayList<>();

        for (String id : deletionRequest.getIds()) {
            if (!id.matches("^\\d+$")) {
                errors.add(id + " is not a valid company id");
            }
        }

        return errors;
    }

}