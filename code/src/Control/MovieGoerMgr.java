// javadocs done
package Control;
import java.util.*;
import java.text.SimpleDateFormat;
import java.lang.IndexOutOfBoundsException;
import Entity.*;
import Entity.Movie.ShowingStatus;
import Boundary.MovieGoerInterface;
import Control.StaffMgr.LoginFeedback;

/**
 * This is a controller for all moviegoer module functions. It displays desired functionality based on user selection. <br>
 * Functionality includes:
 * <ul>
 * <li>List movies (All, Top 5 by rating, Top 5 by Sales)</li>
 * <li>View movie details, reviews and ratings</li>
 * <li>Check seat availability and selection of seat/s.</li>
 * <li>Book and purchase ticket</li>
 * <li>View booking history</li>
 * </ul>
 * 
 * @see MovieGoerInterface
 * @author SS3 Group 8
 * 
 */
public class MovieGoerMgr extends PersonMgr {
    private static MovieGoerMgr movieGoerMgr = null; //singleton class
    private MovieGoerInterface movieGoerInterface = MovieGoerInterface.getInstance();
    private ArrayList<Date> holidays = null; //list of holidays
    private ArrayList<Movie> movies = null; //list of movie information
    private ArrayList<Booking> bookings = null; //list of booking records
    private List<MovieShowing> movieShowings = null; //list of information for movies to be shown
    private MovieGoerMgr() {} //singleton class

    public static MovieGoerMgr getInstance() {
        if (movieGoerMgr == null) {
        	movieGoerMgr = new MovieGoerMgr();
        }
        return movieGoerMgr;
    }
    /**
     * Loads relevant data into this instance of MovieGoerMgr<br>
     * Initializes the following data:
     * <ul>
     * <li>Holidays</li>
     * <li>Movies</li>
     * <li>Bookings</li>
     * <li>Showtimes</li>
     * </ul>
     * 
     * @param holidays ArrayList of the holiday dates
     * @param movies ArrayList of all movie information
     * @param bookings ArrayList of booking records
     * @param movieShowings ArrayList of showtimes
     */
    public void initialize(ArrayList<Date> holidays, ArrayList<Movie> movies, ArrayList<Booking> bookings, ArrayList<MovieShowing> movieShowings) {
        this.holidays = holidays;
        this.movies = movies;
        this.bookings = bookings;
        this.movieShowings = movieShowings;
    }
    /**
     * Books movie ticket(s) <br>
     * 
     * Summary of method functionality:
     * <ol>
     * <li>Lets user select desired movie</li>
     * <li>Checks if ticket is available</li>
     * <li>Asks user for desired showtime (if ticket is available)</li>
     * <li>Asks user to choose seats</li>
     * <li>Asks for user details (name, phone, email and age group)</li>
     * </ol><br><br>
     * User can search for past bookings under his/her name
     */
    public void movieBooking() {
        String movieName, movieGoerName, bookingId, name, email, mobileNumber, transactionID;
        int index, row, col;
        double price = 0;
        Boolean agePrice = null;
        int discount;
        Movie movieToBook;
        MovieShowing movieShowing;
        List<Movie> searchResult;
        List<MovieShowing> movieShowings;
        Seat[][] seats;
        Seat seat = null;

        searchResult = searchMovies();
        if (searchResult.size() == 0) {
            return;
        }

        while (true) {
            index = movieGoerInterface.scanInteger("Enter the Movie ID that you want to book: ");
            // catch array exception
            try {
                movieToBook = searchResult.get(index-1);
                break;
            } catch (IndexOutOfBoundsException e) {
                movieGoerInterface.displayLine("Invalid index! Please try again");
            }
        }

        movieShowings = listMovieShowing(movieToBook);
        if (movieShowings.size() == 0) {
            movieGoerInterface.displayLine("The movie is currently not showing in any Cinemas.");
            return;
        }
        for (MovieShowing show: movieShowings) {
            movieGoerInterface.displayLine("\n" + (movieShowings.indexOf(show)+1) + ")\n" + show.displayMovieInfo());
        }

        while (true) {
            index = movieGoerInterface.scanInteger("\nEnter the Show ID that you want to book: ");
            // catch array exception
            try {
                movieShowing = movieShowings.get(index-1);
                break; 
            } catch (IndexOutOfBoundsException e) {
                movieGoerInterface.displayLine("Invalid index! Please try again");
            } 
        }

        seats = getSeats(movieShowing);
        printLayout(seats);
        
        int numPax;
        double totalPrice = 0;
        
        
        numPax = movieGoerInterface.scanInteger("\nHow many tickets would you like to buy?" );
        
        for (int i = 0; i<numPax; i++) {
        	movieGoerInterface.displayLine("\nWhich seat would you like to book?");
            while (true) { 
            	System.out.println("\nEnter row (A-J): ");
            	Scanner sc = new Scanner(System.in); 
                char rowChar = sc.next().charAt(0); 
                int ascii = (int) rowChar - 64;
            	
                row = ascii;
                col = movieGoerInterface.scanInteger("Enter column (1-10): ");
                // catch array errors
                try {
                    seat = selectSeat(movieShowing, row, col); 
                } catch (IndexOutOfBoundsException e) {
                    movieGoerInterface.displayLine("Wrong row or column number, please try again");
                    continue;
                }
                if (seat != null) {
                    movieGoerInterface.displayLine("Seat successfully booked");
                    break;
                }
                movieGoerInterface.displayLine("Seat selection unsuccessful!\nMake sure you select an empty seat");
            }
            
            while(true) {
            	movieGoerInterface.displayLine("Are you a child, adult or senior citizen?\n1) Child\n2) Adult\n3) Senior Citizen\n");
            	discount = movieGoerInterface.scanInteger("Enter your choice: " );
            	if(discount == 1 | discount == 3){
            		agePrice = true;
            		break;
    	        }
    	        	
    	        else if(discount == 2 ) {
    	        	agePrice = false;
    	        	break;
    	        } else continue;
            }
            
            price = calculatePrice(movieShowing, agePrice);
        	totalPrice = totalPrice + price;
        }
        
        movieGoerInterface.displayLine("Your total price is: " + totalPrice + " SGD");

        name = movieGoerInterface.scanLine("\nPlease enter your name: ");
        mobileNumber = movieGoerInterface.scanString("Please enter your phone number: ");
        email = movieGoerInterface.scanString("Please enter your email address: ");

        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmm");
        transactionID = movieShowing.getCinema().getCinemaCode() + fmt.format(new Date());

        bookings.add(new Booking(new MovieTicket(movieShowing, seat, totalPrice), new Payment(transactionID, name, mobileNumber, email, price)));
        
        for(int i=0;i<numPax;i++) {
        	movieToBook.setRevenue(movieToBook.getRevenue()+1);
        }
        
        

        movieGoerInterface.displayLine("\nBooking successful");
    }

    
    /**
     * Search movies by name <br>
     * If user does not enter name and hits enter, displays all currently showing movies.
     * @return ArrayList of Movie objects
     */
    public ArrayList<Movie> searchMovies() {
        ArrayList<Movie> searchResult = new ArrayList<Movie>();
        String movieName;

        movieName = movieGoerInterface.scanLine("\nPlease enter the movie name: ");

        // Search by name
        for (Movie movie: movies) {
        	if (movie.getTitle().toLowerCase().contains(movieName.toLowerCase())){
    			if(movie.getTitle().equals(movie.getTitle())) {
    				if(!(movie.getShowingStatus() == ShowingStatus.ENDOFSHOWING) & !(movie.getShowingStatus() == ShowingStatus.COMINGSOON)) {
    					searchResult.add(movie);
    				}
    			}
        	}
        }

        // Display result
        if (searchResult.size() != 0) {
            movieGoerInterface.displayLine("\nSearch result:");
            for (Movie movie: searchResult) {
                movieGoerInterface.displayLine((searchResult.indexOf(movie)+1) + ") " + movie.getTitle() + " ("+ movie.getShowingStatus() + ")");
            }
        } else {
            movieGoerInterface.displayLine("No matching movie found!");
        }

        return searchResult;
    }
    
    /**
     * Checks booking history by name
     */
    public void displayBookingHistory() {
        String CustName;
        ArrayList<Booking> bookingsByUser = new ArrayList<Booking>();
  
        CustName = movieGoerInterface.scanLine("Please enter your name: ");

        for (Booking booking: bookings){
            if (booking.getPaymentInfo().getCustName().equals(CustName)){
                bookingsByUser.add(booking);
            }
        }

        if (bookingsByUser.size() != 0) {
            movieGoerInterface.displayLine("\nHere are your bookings:\n");
            for (Booking booking: bookingsByUser) {
            	movieGoerInterface.displayLine("---------------------------------------------------");
                movieGoerInterface.displayLine("Booking: " + (bookingsByUser.indexOf(booking)+1) + "\n" + booking.displayBooking().toString() + "\n");
            }
        } else {
            movieGoerInterface.displayLine("\nNo booking record found");
        }
    }
    /**
     * Display detailed movie information.
     * @see Movie#displayInfo()
     */
    public void displaySearchedMovies() {
    	ArrayList<Movie> movies = searchMovies();
        if (movies.size() == 0) {
            return;
        }
        ArrayList<Review> reviews;
        int index;

        while (true) {
            index = movieGoerInterface.scanInteger("\nPlease enter the Movie ID: ");
            // catch array errors
            try {
                reviews = movies.get(index-1).getReviews();
                break;
            } catch (IndexOutOfBoundsException e) {
                movieGoerInterface.displayLine("Wrong index, please try again");
            }
        }
        
        movieGoerInterface.displayLine(movies.get(index-1).displayInfo());
        movieGoerInterface.displayLine(
                "===================================\n" +
                "          Past Reviews      \n" +
                "===================================" );
        if (reviews.size() == 0) {
        	movieGoerInterface.displayLine("There are currently no reviews\n");
        	}
        else{
        	for (int i =0;i<reviews.size();i++){
            	Review review = reviews.get(i);
                movieGoerInterface.displayLine(i+1 + ") Content: " + review.display() + "\n   Rating: " + review.getRating() +"\n");
        	}
        }
    }
    /**
     * Add review to selected movie
     */
    public void addReview() {
    	ArrayList<Movie> results = searchMovies();
        if (results.size() == 0) {
            return;
        }
        int index, rating;
        String content;
        Movie movie;

        while (true) {
            index = movieGoerInterface.scanInteger("Please enter the movie ID: ");
            // catch array error
            try {
                movie = results.get(index-1);
                break;
            } catch (IndexOutOfBoundsException e) {
                movieGoerInterface.displayLine("Wrong index, please try again");
            }
        }

        content = movieGoerInterface.scanLine("Please enter review content:");
        while (true) {
            rating = movieGoerInterface.scanInteger("Please enter review rating (0-5): ");
            if (rating >= 0 && rating <= 5) {
                break;
            } else {
                movieGoerInterface.displayLine("Rating out of range, please try again");
            }
        }
        movie.addReview(new Review(content, rating));
        calculateRating(movie);

        movieGoerInterface.displayLine("Review successfully added");
    }

    /**
     * Print the seat layout of the cinema of specific show
     * @param seats 2D Array of seats containing Seat objects
     */
    public void printLayout(Seat[][] seats){
    	int alpha = 65;
    	
    	movieGoerInterface.displayLine("____");
    	movieGoerInterface.displayLine("                 " + "SCREEN");
    	movieGoerInterface.displayLine("");
        for (int i = 0; i < seats.length; i++){
        	movieGoerInterface.display((char)alpha + "   ");
        	
            for(int j = 0; j < seats[i].length; j++){
            	if(j == (seats.length/2)) {
            		System.out.print("   ");
            	}
                movieGoerInterface.display(seats[i][j].toString()); 
            }
            
            movieGoerInterface.display("   "+(char)alpha);
            alpha++;
            movieGoerInterface.displayLine("");
        }
        movieGoerInterface.displayLine("");
        movieGoerInterface.displayLine("                " + "ENTRANCE");
        movieGoerInterface.displayLine("____");
    }
    /**
     * List all movies
     */
    public void listMovies(){
        movieGoerInterface.displayLine("\nThis is the list of all movies");
        ArrayList<Movie> movies1 = new ArrayList<Movie>();
        for (Movie movie: movies) {
        	if ( !(movie.getShowingStatus().equals(ShowingStatus.ENDOFSHOWING)) ) {
        		movies1.add(movie);
        	}
        }
        for (Movie movie: movies1){
            movieGoerInterface.displayLine("\n" + (movies1.indexOf(movie)+1) + ") " + movie.displayInfo());
            ArrayList<Review> reviews = movie.getReviews();
            movieGoerInterface.displayLine(
                    "===================================\n" +
                    "          Past Reviews      \n" +
                    "===================================" );
            if (reviews.size() == 0) {movieGoerInterface.displayLine("There are currently no reviews\n");}
            else{
	            	for (int i =0;i<reviews.size();i++) 
	            	{
		            	Review review = reviews.get(i);
		                movieGoerInterface.displayLine(i+1 + ") Content: " + review.display() + "\n   Rating: " + review.getRating() +"\n");
	            	}

	            }
        }
    }
    /**
     * List top 5 by sale, does not display Coming Soon and End of Showing
     */

	  public void listMoviesBySale() {
	
	      List<Movie> topMovies = null;
	      ArrayList<Movie> movies1 = new ArrayList<Movie>();
	        for (Movie movie: movies) {
	        	if ( !(movie.getShowingStatus().equals(ShowingStatus.ENDOFSHOWING) | (movie.getShowingStatus().equals(ShowingStatus.COMINGSOON)) )) {
	        		movies1.add(movie);
	        	}
	        }
	      
	      ArrayList<Movie> moviesNowShowing = new ArrayList<Movie>();
	
	      // sort the movies by sale
	  
		  for(Movie movie : movies1){
			 
				  moviesNowShowing.add(movie);
			  
		  }
	  
	  
		  Collections.sort(moviesNowShowing, new Comparator<Movie>() {
		      @Override
		      public int compare(Movie movie1, Movie movie2) {
		          return movie2.getRevenue() - movie1.getRevenue();
		   
		      }
		  });
		
		  if (moviesNowShowing.size() < 5) {
		      topMovies = moviesNowShowing;
		  } else {
		      topMovies = moviesNowShowing.subList(0, 5);
		  }
		
		  movieGoerInterface.displayLine("The top 5 movies by sales are:");
		  for (Movie movie: topMovies) {
		      movieGoerInterface.displayLine(topMovies.indexOf(movie)+1 + ") " + movie.getTitle()  + ", Total number of tickets sold: " + movie.getRevenue());
		      }
		  }

    
	    /**
	     * List top 5 by rating, does not show the following:
	     * <ul>
	     * <li>End of Showing</li>
	     * <li>Coming Soon</li>
	     * <li>Shows with no rating</li>
	     * <li>Shows with fewer than 2 reviews</li>
	     * </ul>
	     */

    public void listMoviesByRating() {
    	List<Movie> topMovies = null;
	      ArrayList<Movie> movies1 = new ArrayList<Movie>();
	        for (Movie movie: movies) {
	        	if ( !(movie.getShowingStatus().equals(ShowingStatus.ENDOFSHOWING) || (movie.getShowingStatus().equals(ShowingStatus.COMINGSOON)) || (Double.isNaN(movie.getRating()))|| (movie.getReviews().size()<2)) ) {
	        		movies1.add(movie);
	        	}
	        }
	      
	      ArrayList<Movie> moviesNowShowing = new ArrayList<Movie>();
	
	      // sort the movies by sale
	      
	      for(Movie movie : movies1){
	    	 
	    	  moviesNowShowing.add(movie);
	      }
	      Collections.sort(moviesNowShowing, new Comparator<Movie>() {
	          @Override
	          public int compare(Movie movie1, Movie movie2) {
	              return Double.compare(movie2.getRating(),movie1.getRating());
	       
	          }
	      });
	
	      if (moviesNowShowing.size() < 5) {
	          topMovies = moviesNowShowing;
	      } else {
	          topMovies = moviesNowShowing.subList(0, 5);
	      }
	
	      movieGoerInterface.displayLine("The top 5 movies by Ratings are:");
	      for (Movie movie: topMovies) {
	          movieGoerInterface.displayLine(topMovies.indexOf(movie)+1 + ") " + movie.getTitle()  + ", Rating: " + movie.getRating());
	      }
    }
	      
    /**
     * Handles interface for Staff Login<br>
     * Staff Username and Password are both "1"
     * @see StaffMgr#login(String, String)
     */
    public void staffLogin() {
        String username, password;
        Scanner sc = new Scanner(System.in); 
        Scanner sc1 = new Scanner(System.in); 
        System.out.println("Enter username: ");
        username = sc.nextLine();
        System.out.println("Enter Password: ");
        password = sc1.nextLine();

        LoginFeedback feedback = StaffMgr.getInstance().login(username, password);
        switch (feedback) {
            case LOGINSUCCESS:
            	movieGoerInterface.displayLine("Login successful");
                break;
            case ALREADYLOGGEDIN:
                break;
            case WRONGUSERNAMEPASSWORD:
            	movieGoerInterface.displayLine("Wrong username or password, please try again");
                break;
            default:
                break; 
        } 
    }
    /**
     * Returns all the showtime information of a selected movie
     * @param movie Movie object of selected movie
     * @return ArrayList of MovieShowing object
     */
    public ArrayList<MovieShowing> listMovieShowing(Movie movie) {
        ArrayList<MovieShowing> results = new ArrayList<MovieShowing>();
        for (MovieShowing movieShowing: movieShowings) {
            if (movieShowing.getMovie().getTitle().equals(movie.getTitle())) {
                results.add(movieShowing);
            }
        }
        return results;
    }
    /**
     * Gets the seat information of a specific showtime
     * @param movieShowing Specific movie showtime
     * @return A 2D seat array
     */
    public Seat[][] getSeats(MovieShowing movieShowing) {
        return movieShowing.getCinema().getSeatLayout();
    }
    /**
     * Select seat during booking
     * @param movieShowing Showtime of movie that user is booking
     * @param row Row number of seat
     * @param col Column number of seat
     * @return The seat selected
     */
    public Seat selectSeat(MovieShowing movieShowing, int row, int col) throws IndexOutOfBoundsException {
    	Seat[][] seats = movieShowing.getCinema().getSeatLayout();
        Seat seat = null;
        int i, count = 0;
        row--;

        // ignore non exist seats and get the correct seat according to row and col number
        for (i=0; i<seats[row].length; ++i) {
            if (seats[row][i].getStatus() != Seat.enumSeat.NOTEXIST) {
                count++;
                if (count == col) {
                    seat = seats[row][i];
                    break;
                }
            }
        }

        if (seat.getStatus() == Seat.enumSeat.NOTTAKEN) {
            seat.setStatus(Seat.enumSeat.TAKEN);
            seat.setID(row, col);
            return seat;
        } else {
            return null;
        }

    }
    /**
     * Calculate the price of tickets <br>
     * Ticket has a base price, final price depends on type of moviegoer
     * @param movieShowing The movie user wants to book
     * @param discount Whether the movie goer can enjoy discount
     * @return The final price of the ticket
     */
    public double calculatePrice(MovieShowing movieShowing, boolean discount) {
        double price;
        Movie movie;
        Date showTime;

        movie = movieShowing.getMovie();
        price = movie.getBasePrice();
        showTime = movieShowing.getShowTime();
        // check type of movie
        switch (movie.getTypeOfMovie()) {
            case DIGITAL:
            	price = price + 1.0;
            	break;
            case BLOCKBUSTER:
                price = price + 2.0;
                break;
            case NORMAL:
                break;
        }
        // check class of cinema
        switch (movieShowing.getCinema().getClassOfCinema()) {
            case GOLD:
                price = price + 2;
                break;
            case MAX:
                price = price + 5.0;
                break;
            case NORMAL:
                break;
        }
        // check age of movie goer
        if (discount == true) {
            price = price * 0.5;
        }
        // check if Saturday of Sunday
        SimpleDateFormat fmt1 = new SimpleDateFormat("EEEE");
        if ( (fmt1.format(showTime).equals("Saturday")) | (fmt1.format(showTime).equals("Sunday")) ){
        	price = price + 2.0;
        }
        else {
	        // check if holiday
	        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
	        for (Date holiday: holidays) {
	            if (fmt.format(holiday).equals(fmt.format(showTime))) {
	                price = price + 2.0;
	                break;
	            }
	        }
        }

        return price;
    }
}