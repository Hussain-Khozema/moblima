package Entity;

import java.io.Serializable;


/**
 * Seat Class contains details for each seat in a cinema
 * @see MovieGoerMgr
 * @see StaffMgr
 * @author SS3 Group 8
 *
 */
public class Seat implements Serializable{

    /**
     * Enum for Seat Status
     * @author SS3 Group 8
     *
     */
    public enum enumSeat { NOTEXIST, TAKEN, NOTTAKEN }

    /**
     * Seat ID
     */
    private String seatID;

    /**
     * Information on Seat Status (Enum)
     */
    private enumSeat seatStatus;

    /**
     * Constructor for Seat Object
     * @param status Seat Status (Enum)
     * @param seatID String value of Seat ID
     */
    public Seat(enumSeat status, String seatID) {
        this.seatStatus = status;
        this.seatID = seatID;
    }

    /**
     * Used for printing seat layout
     */
    public String toString() {
        if(seatStatus == enumSeat.NOTEXIST){
            return "   ";
        }
        else if (seatStatus == enumSeat.TAKEN){
            return "[X]";
        }
        else {
            return "[ ]";
        }
    }

    /**
     * Gets Seat Status
     * @return Seat Status (Enum)
     */
    public enumSeat getStatus() {
        return seatStatus;
    }

    /**
     * Sets Seat Status
     * @param status New Seat Status (Enum)
     */
    public void setStatus(enumSeat status) {
        seatStatus = status;
    }

    /**
     * Sets seat ID
     * @param row Row which this seat resides in
     * @param col Column which this seat resides in
     */
    public void setID(int row, int col) {
        char base = 'A';
        char letterRow = (char)((int)base + row);
        seatID = letterRow + "" + col;
    }

    /**
     * Gets Seat ID
     * @return String value of SeatID
     */
    public String getSeatID() {
        return seatID;
    }
}
