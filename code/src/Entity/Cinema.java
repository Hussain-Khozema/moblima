package Entity;

import java.io.Serializable;

import Entity.Seat;

/**
 * Cinema class contains information such as Seat Layout, Cinema Code etc.<br>
 * 
 * @see MovieGoerMgr
 * @see StaffMgr
 * @author SS3 Group 8
 *
 */
public class Cinema implements Serializable{

    /**
     * Enum for Class of Cinema for differential pricing.
     *
     */
    public enum ClassOfCinema { GOLD, MAX, NORMAL }
	/**
	 * Contains Class of Cinema for differential pricing.
	 */
	private ClassOfCinema classOfCinema;
    /**
     * Cinema code used for Transaction ID.
     */
    private String cinemaCode;
	/**
	 * 2D Array containing Seat Layout
	 */
	private Seat[][] seats;
	/**
	 * Cinema Index
	 */
	private int cinemaIndex;
	/**
	 * Number of Empty Seats
	 */
	private int numOfEmptySeat;
	/**
	 * Number of rows for Seat Layout
	 */
	private int row;
	/**
	 * Number of column for Seat Layout
	 */
	private int column;
	/**
	 * Constructor for Cinema Object
	 * 
	 * @param cinemaClass Class of Cinema (i.e. GOLD)
	 * @param cinemaCode Used for Transaction ID
	 * @param row Number of rows in Seat Layout
	 * @param column Number of columns in Seat Layout
	 * @param seats Seat Layout
	 * @param cinemaID Cinema ID
	 * @param numOfEmptySeat Number of Empty Seats
	 */
	public Cinema(ClassOfCinema cinemaClass, String cinemaCode, int row, int column, Seat[] seats, int cinemaID, int numOfEmptySeat){
		this.classOfCinema = cinemaClass;
        this.cinemaCode = cinemaCode;
		this.row = row;
		this.column = column;
		this.seats = new Seat[row][column];
		for (int i=0; i<row; i++){
			for (int j=0; j<column; j++){
				this.seats[i][j]=seats[i*column+j];
                this.seats[i][j].setID('A'+i, j+1);
			}
		}
		this.cinemaIndex = cinemaID;
		this.numOfEmptySeat = numOfEmptySeat;
	}

	/**
	 * Gets Class of this Cinema
	 * @return Class Of Cinema (Enum)
	 */
	public ClassOfCinema getClassOfCinema(){
		return classOfCinema;
	}

	/**
	 * Gets specific seat in this Cinema
	 * @param row Row of Seat
	 * @param col Column of Seat
	 * @return Returns Seat
	 */
	public Seat getSeat(int row, int col){
		return seats[row][col];
	}

	/**
	 * Gets this Cinema's index
	 * @return Cinema Index
	 */
	public int getCinemaIndex(){
		return cinemaIndex;
	}

    /**
     * Gets this Cinema's code
     * @return Cinema Code
     */
    public String getCinemaCode() {
        return cinemaCode;
    }

	/**
	 * Gets number of empty seats
	 * @return Number of Empty Seats
	 */
	public int getnumOfEmptySeat(){
		return numOfEmptySeat;
	}
	
	/**
	 * Sets Number of Empty Seats (Accepts Integer)
	 * @param numOfEmptySeat Integer
	 */
	public void setNumOfEmptySeat(int numOfEmptySeat){
		this.numOfEmptySeat = numOfEmptySeat;
	}

	/**
	 * Returns 2D Array of Seat Layout
	 * @return Seat Layout
	 */
	public Seat[][] getSeatLayout(){
		return seats;
	}

	/**
	 * Gets row number
	 * @return Row Number
	 */
	public int getRow(){
		return row;
	}

	/**
	 * Gets column number
	 * @return Column Number
	 */
	public int getCol(){
		return column;
	}

}