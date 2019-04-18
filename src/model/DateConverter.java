package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.util.StringConverter;

/**
 * This class is a singleton which converts date to particular pattern;
 * @author Patryk Kłos
 * @version 1.0
 * @version 2019-03-22
 */
public class DateConverter extends StringConverter<LocalDate> {

	/**
	 * @see DateTimeFormatter 
	 *      The object of DateTimeFomratter which converts date to some pattern
	 */
	private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

	@Override
	public String toString(LocalDate date) {
		if (date == null)
			return "";

		return dateTimeFormatter.format(date);
	}

	@Override
	public LocalDate fromString(String string) {

		if (string == null || string.trim().isEmpty())
			return null;

		return LocalDate.parse(string, dateTimeFormatter);
	}

	/**
	 * @see DateTimeFormatter
	 * @return the dateTimeFomratter of this class
	 */
	public DateTimeFormatter getDateTimeFormatter() {
		return dateTimeFormatter;
	}

}
