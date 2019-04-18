package model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InstructionReader {

	private String filePath, fileName;

	/**
	 * Contructor with default path defined on /resources/ directory
	 * 
	 * @param fileName Name of file with instruction
	 */
	public InstructionReader(String fileName) {
		this.filePath = "resources/instructions/" + fileName;
	}

	/**
	 * 
	 * @param filePath this is path to file directory
	 * @param fileName fileName Name of file with instruction
	 */
	public InstructionReader(String filePath, String fileName) {
		this.fileName = fileName;
		this.filePath = filePath;
	}

	/**
	 * @see String
	 * @return String with instruction from file
	 */
	public String getInstructionFromFile() {
		StringBuilder sb = new StringBuilder();
		InputStream stream;
		try {
			System.out.println(filePath);

			if (fileName == null) {
				stream = getClass().getClassLoader().getResourceAsStream(filePath);
			} else {
				stream = getClass().getClassLoader().getResourceAsStream(filePath + fileName);
			}
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader bReader = new BufferedReader(reader);
			String line = null;
			while ((line = bReader.readLine()) != null) {
				sb.append(line + "\n");
			}
			bReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

}
