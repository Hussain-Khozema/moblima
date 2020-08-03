package Boundary;

import Control.StaffMgr;;

/**
 * Interface for movie-goer.
 * Functionality includes: <br>
 * <ul>
 * <li>Create/Update/Remove Movie Listing</li>
 * <li>Create/Update/Remove Cinema Showtime</li>
 * <li>List top 5 movies by sales</li>
 * <li>List top 5 movies by rating</li>
 * <li>Change movie base price</li>
 * <li>Generate sales report</li>
 * <li>Add/Remove holidays</li>
 * <li>Logout</li>
 * </ul><br>
 * All functionality is handled by StaffMgr<br>
 * Note that logging out switches over to MovieGoerInterface
 * @see MovieGoerInterface
 * @see StaffMgr 
 * @author SS3 Group 8
 * 
 */
public class StaffInterface extends PersonInterface {
    /**
     * StaffInterface is a singleton class.
     * It can only be created by itself.
     */
    private static StaffInterface staffInterface = null;
     /**
     * Since StaffInterface is a singleton class, it can only be created by itself.
     */
    private StaffInterface() {}
    /**
     * Gets a staff interface.<br>
     * If StaffInterface has not been created yet, create a new staff interface then return it
     * @return staffInterface
     */
    public static StaffInterface getInstance() {
        if (staffInterface == null) {
            staffInterface = new StaffInterface();
        }
        return staffInterface;
    }
     /**
     * Console interface to interact with user. <br>
     * Summary of method functionality: <br>
     * <ol>
     * <li>Print options</li>
     * <li>Get user input</li>
     * <li>Calls desired method from StaffMgr controller</li>
     * </ol>
     */
     
    public void interact() {
        int choice;
        StaffMgr staffMgr = StaffMgr.getInstance();

        displayMenu();
        choice = scanInteger("Enter your choice: ");
        switch (choice) {
            case 1:
            	staffMgr.createMovieListing();
                break;
            case 2:
            	staffMgr.updateMovieListing();
                break;
            case 3:
            	staffMgr.removeMovieListing();
                break;
            case 4:
            	staffMgr.createShowtime();
                break;
            case 5:
            	staffMgr.updateShowtime();
                break;
            case 6:
            	staffMgr.removeShowtime();
                break;
            case 7:
            	staffMgr.updateTicketPrice();
                break;
            case 8:
            	staffMgr.listMoviesBySale();
            	break;
            case 9:
            	staffMgr.listMoviesByRating();
            	break;
            case 10:
            	staffMgr.getSalesReport();
                break;
            case 11:
            	staffMgr.confirgureHoliday();
                break;
            case 12:
            	staffMgr.signOut();
                break;
            case 13:
            	staffMgr.exit();
                break;
            default:
                System.out.println("Wrong input, please try again");
                break;
        }
    }
     /**
     * Print the menu option
     */
    public void displayMenu() {
        System.out.println(
        "\n=================================\n" +
        "          Main Menu          \n" +
        "=================================\n" +
                        
        
        "1)  Create movie listing\n" +
        "2)  Update movie listing\n" +
        "3)  Remove movie listing\n" +
        "4)  Create cinema showtime\n" +
        "5)  Update cinema showtime\n" +
        "6)  Remove cinema showtime\n" +
        "7)  Change movie base price\n" +
        "8)  List top 5 movies by sale\n" +
        "9)  List top 5 movies by rating\n" +
        "10) Get sale report\n" +
        "11) Configure holidays\n" +
        "12) Logout\n" +
        "13) Exit");
    }
}