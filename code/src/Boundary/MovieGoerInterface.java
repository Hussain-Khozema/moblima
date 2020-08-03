package Boundary;

import Control.MovieGoerMgr;

/**
 * Interface for movie-goer.
 * Functionality includes: <br>
 * <ul>
 * <li>List all movies</li>
 * <li>Search for movies</li>
 * <li>Book movie ticket(s)</li>
 * <li>Add movie review</li>
 * <li>List top 5 movies by sales</li>
 * <li>List top 5 movies by rating</li>
 * <li>Login as staff</li>
 * </ul><br>
 * All functionality is handled by MovieGoerMgr<br>
 * Note that logging in as staff switches over to StaffInterface
 * @see StaffInterface
 * @see MovieGoerMgr
 * 
 * @author SS3 Group 8
 */
public class MovieGoerInterface extends PersonInterface {
    /**
     * MovieGoerInterface is a singleton class.
     * It can only be created by itself.
     */
	
//	In object-oriented programming, a singleton class is a class that can have only one object (an instance of the class) at a time.
//     After first time, if we try to instantiate the Singleton class, the new variable also points to the first instance created. 
    private static MovieGoerInterface movieGoerInterface = null;
    /**
     * Since MovieGoerInterface is a singleton class, it can only be created by itself.
     */
    private MovieGoerInterface() {}
    
    /**
     * Returns instance of MovieGoerInterface. If instance does not exist,
     * creates a new MovieGoerInterface.
     * @return movieGoerInterface
     */
    public static MovieGoerInterface getInstance() { // static method to create instance of Singleton class
        if (movieGoerInterface == null) {
            movieGoerInterface = new MovieGoerInterface();
        }
        return movieGoerInterface;
    }
    /**
     * Console interface to interact with user. <br>
     * Summary of method functionality: <br>
     * <ol>
     * <li>Print options</li>
     * <li>Get user input</li>
     * <li>Calls desired method from MovieGoerMgr controller</li>
     * </ol>
     */
    public void interact() {
        int choice;
        MovieGoerMgr movieGoerMgr = MovieGoerMgr.getInstance();

        displayMenu();
        choice = scanInteger("Enter your choice: ");
        switch (choice) {
            case 1:
            	movieGoerMgr.listMovies();
                break;
            case 2:
            	movieGoerMgr.displaySearchedMovies();
                break;
            case 3:
            	movieGoerMgr.movieBooking();
                break;
            case 4:
            	movieGoerMgr.displayBookingHistory();
                break;
            case 5:
            	movieGoerMgr.addReview();
                break;
            case 6:
            	movieGoerMgr.listMoviesBySale();
                break;
            case 7:
            	movieGoerMgr.listMoviesByRating();
                break;
            case 8:
            	movieGoerMgr.staffLogin();
                break;
            case 9:
            	movieGoerMgr.exit();
                break;
            default:
                System.out.println("Wrong input, please try again");
                break;
        }
    }
    /**
     * Prints options
     */
    public void displayMenu() {
        System.out.println(
        "\n=================================\n" +
        "          Main Menu          \n" +
        "=================================\n" +
        
        "1) List all movies    \n" +
        "2) Search for movies\n" +
        "3) Book movie ticket(s)\n" +
        "4) Check booking history\n" +
        "5) Add movie review\n" +
        "6) List top 5 movies by sale\n" +
        "7) List top 5 movies by rating\n" +
        "8) Staff login\n" +
        "9) Exit\n");
    }
}