package fr.ebiz.cdb.validator;

import fr.ebiz.cdb.binding.ComputerDeleteRequest;

import java.util.ArrayList;
import java.util.List;

public class ComputerDeletionValidator {

    /**
     * Validates computer deletion.
     *
     * @param deletionRequest deletionRequest
     * @return errors
     */
    public static List<String> validate(ComputerDeleteRequest deletionRequest) {
        List<String> errors = new ArrayList<>();

        for (String id : deletionRequest.getSelection()) {
            if (!id.matches("^\\d+$")) {
                errors.add(id + " is not a valid company id");
            }
        }

        return errors;
    }

}