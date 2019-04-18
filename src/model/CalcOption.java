package model;

/**
 * Class that contains options for OptionList
 * 
 * @see model.OptionList
 * @author Patryk Kłos
 * @version 1.0
 * @version 2019-03-22
 *
 */
public class CalcOption {
	private String description;
	private CalcOptionType type;

	/**
	 * 
	 * @param description this is text for option
	 * @param type        this is type of particaular option
	 */
	public CalcOption(String description, CalcOptionType type) {
		this.description = description;
		this.type = type;
	}

	public CalcOption() {
		this("None calc", CalcOptionType.NONE);
	}

	/**
	 * 
	 * @return the decsription of object of this class
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description here you can set the description for object
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return type of object of this class
	 */
	public CalcOptionType getType() {
		return type;
	}

	/**
	 * @param type is the type of CalcOption of this class
	 */
	public void setType(CalcOptionType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return description;
	}

	/**
	 * This is static enumerator with types of CalcOption
	 * @author Patryk Kłos
	 * @version 1.0
	 */
	public static enum CalcOptionType {
		/**
		 * type for none option
		 */
		NONE, 
		/**
		 * type for average option
		 */
		AVG,
		/**
		 * type for sum option
		 */
		SUM,
		/**
		 * type for min and max option
		 */
		MIN_MAX;
	}
}
