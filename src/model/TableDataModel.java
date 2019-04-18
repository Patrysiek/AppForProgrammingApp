package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

/**
 * This class is model for Table
 * 
 * @author Patryk Kłos
 * @version 1.0
 * @version 2019-03-22
 * @param <T> this param define what type of data table contains
 */
public class TableDataModel<T extends Number> {

	/**
	 * ArrayList which contains rows which are ObservableLists due to achieve
	 * various size of table
	 * 
	 * @see ObservableList
	 */
	private ArrayList<ObservableList<T>> rows;

	/**
	 * This ArrayList contains TableColumns which contain ObservableList
	 * 
	 * @see TableColumn
	 * @see ObservableList
	 */
	private ArrayList<TableColumn<ObservableList<T>, T>> columnsList;
	private int columnsAmount, rowsAmount;

	/**
	 * Constructor of this class
	 * 
	 * @param rowsAmount    amount of rows in table
	 * @param columnsAmount amount of columns in table
	 * @param defaultValue  default value in each cell
	 */
	public TableDataModel(int rowsAmount, int columnsAmount, T defaultValue) {
		if (rowsAmount < 0 || columnsAmount < 0 || defaultValue == null)
			throw new RuntimeException("Please pass correct values !");

		this.columnsAmount = columnsAmount;
		this.rowsAmount = rowsAmount;
		columnsList = initWholeTable();
		rows = initRows(defaultValue);
	}

	/**
	 * This constructor makes table with size 10x10
	 * 
	 * @param defaultValue default value in each cell
	 */
	public TableDataModel(T defaultValue) {
		if (defaultValue == null)
			throw new RuntimeException("Default value can't be null !");

		columnsAmount = 10;
		rowsAmount = 10;
		columnsList = initWholeTable();
		rows = initRows(defaultValue);
	}

	/**
	 * this function initialize whole table table an setsCellValueFactory for
	 * columns
	 * 
	 * @see TableColumn#setCellValueFactory(javafx.util.Callback)
	 * @see javafx.util.Callback
	 * @return return whole table
	 */
	private ArrayList<TableColumn<ObservableList<T>, T>> initWholeTable() {

		ArrayList<TableColumn<ObservableList<T>, T>> table = new ArrayList<>();

		for (int i = 0; i < columnsAmount; i++) {
			final int finalIdx = i;
			TableColumn<ObservableList<T>, T> column = new TableColumn<>(String.valueOf(i));
			column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(finalIdx)));
			table.add(column);
		}

		return table;
	}

	/**
	 * This function initializes rows
	 * 
	 * @param defaultValue default values of cells in row
	 * @return rows with default values
	 */
	private ArrayList<ObservableList<T>> initRows(T defaultValue) {

		ArrayList<ObservableList<T>> rows = new ArrayList<>();
		for (int i = 0; i < rowsAmount; i++)
			rows.add(initRowColumns(defaultValue));

		return rows;
	}

	/**
	 * This initialize columns in one row with default value
	 * 
	 * @param defaultValue default values of cells in row
	 * @return one row with defaultValues
	 */
	private ObservableList<T> initRowColumns(T defaultValue) {
		ObservableList<T> row = FXCollections.observableArrayList();
		for (int i = 0; i < columnsAmount; i++) {
			row.add(defaultValue);
		}
		return row;
	}

	/**
	 * This method replace row on particular position in table with new value and
	 * replaced it on particular position in row
	 * 
	 * @param list        row of values
	 * @param columnIndex column of cell that will be changed
	 * @param rowIndex    index of row which contains particular cell
	 * @param value       value which will be set to particular cell
	 * @return row with new values
	 */
	public ObservableList<T> replaceValue(ObservableList<T> list, int columnIndex, int rowIndex, T value) {

		list = FXCollections.observableArrayList(list);
		list.set(columnIndex, value);
		rows.set(rowIndex, list);

		return list;
	}

	/**
	 * 
	 * @return whole table
	 */
	public ArrayList<TableColumn<ObservableList<T>, T>> getWholeTable() {
		return columnsList;
	}

	/**
	 * 
	 * @return ArrayList of rows which are ObersvableList &lt;T&gt;
	 * @see ObservableList
	 */
	public ArrayList<ObservableList<T>> getRows() {
		return rows;
	}

	/**
	 * 
	 * @param rows set rows in table
	 */
	public void setRows(ArrayList<ObservableList<T>> rows) {
		this.rows = rows;
	}

	/**
	 * @return average value of all cells in table
	 */
	public double calculateAverage() {
		int amount = columnsAmount * rowsAmount;
		return calculateSum() / amount;
	}

	/**
	 * @return sum of all cells from table
	 */
	public double calculateSum() {
		double sum = 0;
		for (ObservableList<T> r : rows) {
			for (T value : r) {
				sum += (double) value;
			}
		}
		return sum;
	}

	/**
	 * 
	 * @return string of min and max value from table
	 */
	public String minAndMax() {
		double min = 0;
		double max = 0;
		for (ObservableList<T> r : rows) {
			for (T value : r) {
				double v = (double) value;
				if (v > max)
					max = v;
				if (v < min)
					min = v;
			}
		}
		return "Min: " + min + " Max: " + max;
	}

	/**
	 * @return map which count occurences of particular values
	 */
	public Map<T, Integer> countValuesAmount() {
		HashMap<T, Integer> map = new HashMap<>();

		for (ObservableList<T> r : rows) {
			for (T key : r) {

				if (map.containsKey(key)) {
					Integer value = map.get(key);
					value++;
					map.put(key, value);
				} else {
					map.put(key, 1);
				}

			}
		}
		return map;

	}

}
