package model;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import application.MyLogger;

/**
 * Class which object is responsible for getting tips from resources
 * 
 * @author Patryk Kłos
 * @version 1.0
 * @version 2019-03-22
 * 
 */

public class TipModel {

	public LinkedList<String> tips;
	private int position;

	/**
	 * In the constructor path to file with tips is specified as:
	 * "/application/quotes.txt"
	 */
	public TipModel() {
		tips = new LinkedList<>();

		InputStream stream = TipModel.class.getResourceAsStream("/application/quotes.txt");
		Scanner input = new Scanner(stream);
		do {
			String line = input.nextLine();
			if (line.length() > 0)
				tips.add(line);
		} while (input.hasNextLine());
		input.close();
	}

	/**
	 * @return list for tips
	 * @see LinkedList
	 */
	public LinkedList<String> getTips() {
		return tips;
	}

	/**
	 * @return next element from tips list
	 */
	public String getNext() {
		MyLogger.getLogger().info("Getting next tip...");
		if (position < tips.size() - 1) {
			position++;
		} else {
			position = 0;
		}
		return tips.get(position);
	}

	/**
	 * @return previous element from tips list
	 */
	public String getPrevious() {
		MyLogger.getLogger().info("Getting previous tip...");
		if (position > 0)
			position--;
		else
			position = tips.size() - 1;

		return tips.get(position);
	}

	/**
	 * @return random element from tips list
	 */
	public String initRandomTip() {
		position = new Random().nextInt(tips.size());
		return tips.get(position);

	}
}
