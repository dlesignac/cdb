package fr.ebiz.cdb.service.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import fr.ebiz.cdb.model.Computer;

public class ComputerValidator {

	public static boolean validateName(String name) {
		return true;
	}

	public static boolean validateIntroduced(String str) {
		return isValidDate(str) && LocalDate.parse(str).isBefore(LocalDate.now());
	}

	public static boolean validateDiscontinued(String str, Computer computer) {
		LocalDate date = LocalDate.parse(str);
		return computer.getIntroduced() != null && isValidDate(str) && date.isBefore(LocalDate.now())
				&& date.isAfter(computer.getIntroduced());
	}

	public static boolean isValidDate(String text) {
		if (text == null || !text.matches("\\d{4}-[01]\\d-[0-3]\\d"))
			return false;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		df.setLenient(false);
		try {
			df.parse(text);
			return true;
		} catch (ParseException ex) {
			return false;
		}
	}

}
