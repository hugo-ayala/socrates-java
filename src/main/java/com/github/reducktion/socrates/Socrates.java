package com.github.reducktion.socrates;

import java.util.Optional;

import com.github.reducktion.socrates.extractor.Citizen;
import com.github.reducktion.socrates.extractor.CitizenExtractor;
import com.github.reducktion.socrates.extractor.CitizenExtractorFactory;
import com.github.reducktion.socrates.validator.IdValidator;
import com.github.reducktion.socrates.validator.IdValidatorFactory;

/**
 * Socrates allows you to validate and retrieve personal data from National Identification Numbers across the world.
 */
public class Socrates {

    /**
     * Validate the National Identification Number.
     *
     * @param id the national identification number
     * @param country the country of the national identification number
     * @return true if the {@code id} is valid, false otherwise
     * @throws UnsupportedOperationException if the country is not supported
     */
    public boolean validateId(final String id, final Country country) {
        final IdValidator idValidator = IdValidatorFactory.getValidator(country);
        return idValidator.validate(id);
    }

    /**
     * Extracts the {@link Citizen} from the National Identification Number.
     *
     * @param id the national identification number
     * @param country the country of the national identification number
     * @return the {@link Citizen} wrapped in an {@link Optional}, if the {@code id} is valid
     * @throws UnsupportedOperationException if the country is not supported
     */
    public Optional<Citizen> extractCitizenFromId(final String id, final Country country) {
        final IdValidator idValidator = IdValidatorFactory.getValidator(country);
        final CitizenExtractor citizenExtractor = CitizenExtractorFactory.getExtractor(country);
        return citizenExtractor.extractFromId(id, idValidator);
    }
}
