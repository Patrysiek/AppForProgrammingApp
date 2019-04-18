package model;

import java.util.ArrayList;

import model.CalcOption.CalcOptionType;

/**
 * This class contains list of CalcOption
 * 
 * @see model.CalcOption
 * @author Patryk Kłos
 * @version 1.0
 * @version 2019-03-22
 */
public class OptionList {

	private ArrayList<CalcOption> options;

	/**
	 * Constructor which by default adding three option to the list 
	 * Sum of elements
	 * Average of elements
	 * Mix and min values
	 */
	public OptionList() {
		options = new ArrayList<>();
		options.add(new CalcOption("Sum of elements", CalcOptionType.SUM));
		options.add(new CalcOption("Average of elements", CalcOptionType.AVG));
		options.add(new CalcOption("Max and min value", CalcOptionType.MIN_MAX));
	}

	/**
	 * Method which allows add option to the list of options
	 * 
	 * @param calcOption - this is object of class CalcOption
	 * @see model.CalcOption
	 */
	public void add(CalcOption calcOption) {
		options.add(calcOption);
	}

	/**
	 * Method which allows delete option from the list of options
	 * 
	 * @param index - index of element in list
	 */
	public void remove(int index) {
		options.remove(index);
	}

	/**
	 * 
	 * @return List of options 
	 * @see ArrayList
	 */
	public ArrayList<CalcOption> getOptions() {
		return options;
	}

	/**
	 * 
	 * @param index of option in the ArrayList
	 * @return option from the ArrayList
	 * @see model.CalcOption
	 */
	public CalcOption get(int index) {
		return options.get(index);
	}

}
