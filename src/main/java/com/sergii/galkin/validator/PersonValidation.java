package com.sergii.galkin.validator;

import com.sergii.galkin.model.Person;

public class PersonValidation {

    public static boolean validatePerson(Person person) {
	if (person == null || !person.getNif().matches("^\\d{9}$") || person.getName().length() > 30
		|| person.getAddress().length() > 50 || person.getPhone().matches("^\\+?\\d{9,14}$")) {
	    return false;
	}

	return true;
    }

}
