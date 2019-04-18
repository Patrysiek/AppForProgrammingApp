package application;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Singleton class for writing log of application
 * 
 * @author Patryk Kłos
 * @version 1.0
 * @version 2019-03-22
 */
public class MyLogger {

	private final static MyLogger myLogger = new MyLogger();

	/**
	 * Configures log from properties
	 */
	private MyLogger() {
		try {
			PropertyConfigurator.configure(getClass().getResource("/resources/logger.properties"));
		} catch (Exception e) {
		}
	}

	/**
	 *
	 * @return the logger for writing log
	 */
	private Logger log() {
		try {
			return Logger.getLogger("myLogger");
		} catch (Exception e) {
			System.out.println("fdsafsdfdsafdasf");
			return null;
		}

	}

	public static Logger getLogger() {
		return myLogger.log();
	}
}
