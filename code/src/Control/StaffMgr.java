package Control;

import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import Entity.*;
import Entity.Cinema.ClassOfCinema;
import Entity.Movie.ShowingStatus;
import Entity.Seat.enumSeat;
import Entity.User.TypeOfUser;
import Boundary.StaffInterface;
/**
 * This is a controller for all staff module functions.<br>
 * 
 * Functionality includes:
 * <ul>
 * <li>Login/logout</li>
 * <li>Movie Listingsd (Create, update and remove)</li>
 * <li>Cinema Showtimes (Create, update and remove)</li>
 * <li>Configure system settings (Ticket Prices and holidays)</li>
 * </ul>
 * 
 * @see StaffInterface
 * @author SS3 Group 8
 */
public class StaffMgr extends PersonMgr {
    /**
     * Enumeration for different kinds of staff login feedback<br>
     * 
     * Includes:
     * <ul>
     * <li>WRONGUSERNAMEPASSWORD</li>
     * <li>ALREADYLOGGEDIN</li>
     * <li>LOGINSUCCESS</li>
     * </ul>
     */
    public enum LoginFeedback {WRONGUSERNAMEPASSWORD, ALREADYLOGGEDIN, LOGINSUCCESS}
    /**
     * StaffMgr is a singleton class. <br>
     * It can only be created by itself
     */
    private static StaffMgr StaffMgr = null;
    /**
     * The staff interface
     */
    private StaffInterface staffInterface = StaffInterface.getInstance();
    /**
     * List of all movies
     */
    private List<Movie> movies = null;
    /**
     * List of all booking records
     */
    private List<Booking> bookings = null;
    /**
     * List of the showtimes
     */
    private List<MovieShowing> movieShowings = null;
    /**
     * List of holidays
     */
    private List<Date> holidays = null;
    /**
     * List of cineplexes
     */
    private List<Cineplex> cineplexes = null;
    /**
     * Boolean expression for loggedIn status <br>
     * <i>True when staff logins successful</i>
     */
    private boolean loggedIn = false;
    /**
     * Set staff username as 1
     */
    private static final String staffUsername = "1";
    /**
     * Set staff password as 1
     */
    private static final String staffPassword = "1";
    /**
     * StaffMgr can only be created by itself
     */
    private StaffMgr() {}
    /**
     * Gets a staff controller<br>
     * If staff controller has not been created yet,
     * create a new staff controller then return it
     * @return Staff Controller
     */
    public static StaffMgr getInstance() {
        if (StaffMgr == null) {
            StaffMgr = new StaffMgr();
        }
        return StaffMgr;
    }
    /**
     * Loads relevant data into this instance of MovieGoerMgr<br>
     * Initializes the following data:
     * <ul>
     * <li>Holidays</li>
     * <li>Movies</li>
     * <li>Bookings</li>
     * <li>Showtimes</li>
     * <li>Cineplexes </li>
     * </ul>
     * 
     * @param holidays ArrayList of the holiday dates
     * @param movies ArrayList of all movie information
     * @param bookings ArrayList of booking records
     * @param movieShowings ArrayList of showtimes
     * @param cineplexes ArrayList of cineplexes
     */
    public void initialize(ArrayList<Movie> movies, ArrayList<Booking> bookings, ArrayList<MovieShowing> movieShowings, ArrayList<Date> holidays, ArrayList<Cineplex> cineplexes) {
        this.movies = movies;
        this.bookings = bookings;
        this.movieShowings = movieShowings;
        this.holidays = holidays;
        this.cineplexes = cineplexes;
    }
    /**
     * Creates movie listing
     * Summary of method functionality:
     * <ol>
     * <li>Enter movie title</li>
     * <li>Sets show status (i.e. Coming Soon)</li>
     * <li>Sets type of movie (i.e. Blockbuster)</li>
     * <li>Enter details of movie</li>
     * <li>Enter reviews (if any)</li>
     * </ol><br><br>
     * 
     * Note:<br>
     * If no reviews are entered, an empty ArrayList of reviews is created.
     */
    public void createMovieListing() {
        Movie movie;
        int count, choice, reviewRating, i;
        double basePrice;
        String title, synopsis, director, reviewContent;
        String[] casts;
        Movie.ShowingStatus showingStatus = null;
        Movie.TypeOfMovie typeOfMovie = null;
        List<Review> pastReviews;
        Movie.Censorship censorship = null;

        title = staffInterface.scanLine("\nInput movie title: ");

        staffInterface.displayLine(
        "Showing status:\n" +
        "1) Coming Soon\n" +
        "2) Preview\n" +
        "3) Now Showing\n" +
        "4) End Of Showing");
        choice = staffInterface.scanInteger("Enter ID to set the showing status of the movie: ");
        while (true) {
            	if (choice == 1) {showingStatus = Movie.ShowingStatus.COMINGSOON;}
            	else if (choice == 2) {showingStatus = Movie.ShowingStatus.PREVIEW;}
            	else if (choice == 3) {showingStatus = Movie.ShowingStatus.NOWSHOWING;}
            	else if (choice == 4) {showingStatus = Movie.ShowingStatus.ENDOFSHOWING;}
            	else 
            	{
            		choice = staffInterface.scanInteger("Wrong input, please enter a valid ID: ");
            		continue;
            	}
                break;}
        
        staffInterface.displayLine(
        "\nType of movie:\n" +
        "1) Digital\n" +
        "2) Blockbuster\n" +
        "3) Normal");
        choice = staffInterface.scanInteger("Enter ID to set the type of movie: ");
        while (true) {
            	if (choice == 1) {typeOfMovie  = Movie.TypeOfMovie.DIGITAL;}
            	else if (choice == 2) {typeOfMovie = Movie.TypeOfMovie.BLOCKBUSTER;}
            	else if (choice == 3) {typeOfMovie = Movie.TypeOfMovie.NORMAL;}
            	else 
            	{
            		choice = staffInterface.scanInteger("Wrong input, please enter a valid ID: ");
            		continue;
            	}
                break;}

        synopsis = staffInterface.scanLine("\nEnter synopsis: ");

        director = staffInterface.scanLine("\nEnter Director: ");

        count = staffInterface.scanInteger("\nNumber of casts: ");
        casts = new String[count];
        for (i=0; i<count; ++i) {
            casts[i] = staffInterface.scanLine("Cast " + (i+1) + ": ");
        }

        count = staffInterface.scanInteger("\nNumber of past reviews: ");
        pastReviews = new ArrayList<Review>();
        for (i=0; i<count; ++i) {
            reviewContent = staffInterface.scanLine("\nContent of review "+(i+1)+": ");
            reviewRating = staffInterface.scanInteger("Rating of review "+(i+1)+": ");
            pastReviews.add(new Review(reviewContent, reviewRating));
        }

        basePrice = staffInterface.scanDouble("\nEnter base price of the movie: ");

        staffInterface.displayLine(
        "\nMovie ratings:\n" +
        "1) G\n2) PG\n3) PG13\n4) NC16\n5) M18\n6) R21");
        choice = staffInterface.scanInteger("Enter ID to set the rating for the movie: ");
        while (true) {
            	if (choice == 1) {censorship  = Movie.Censorship.G;}
            	else if (choice == 2) {censorship = Movie.Censorship.PG;}
            	else if (choice == 3) {censorship = Movie.Censorship.PG13;}
            	else if (choice == 4) {censorship = Movie.Censorship.NC16;}
            	else if (choice == 5) {censorship = Movie.Censorship.M18;}
            	else if (choice == 6) {censorship = Movie.Censorship.R21;}
            	else 
            	{
            		choice = staffInterface.scanInteger("Wrong input, please enter a valid ID: ");
            		continue;
            	}
                break;}

        ArrayList<Review> getReviews;
        movie = new Movie(title, showingStatus, typeOfMovie, synopsis, director, casts, 0.0, (ArrayList<Review>)pastReviews, basePrice, censorship, 0);
        calculateRating(movie);
        movies.add(movie);

        staffInterface.displayLine("Movie: " + movie.getTitle() +" created\n");
    }
    /**
     * Updates movie listing<br>
     * Lists all movies, staff selects which movie to update by index<br>
     * Functionality includes: <br>
     * <ul>
     * <li>Showing Status (i.e. Coming Soon)</li>
     * <li>Type of Movie (i.e. Blockbuster)</li>
     * <li>Synopsis</li>
     * </ul><br>
     */

    public void updateMovieListing() {
        int choice, index;
        String synopsis;
        Movie movieToUpdate, newMovie;

        index = listAndSelectMovieWithoutDetails();
        movieToUpdate = movies.get(index);
        newMovie = (Movie)movieToUpdate.clone();

        staffInterface.displayLine(
        "Choose the field that you want to change:\n" +
        "1) Showing status\n" +
        "2) Type of movie\n" +
        "3) Synopsis\n" +
        "4) Finish modifying");

        choice = staffInterface.scanInteger("");
        while (choice != 4) {
            switch (choice) {
                case 1:
                    staffInterface.displayLine(
                            "Showing status:\n" +
                            "1) Coming Soon\n" +
                            "2) Preview\n" +
                            "3) Now Showing\n" +
                            "4) End Of Showing");
                    Movie.ShowingStatus showingStatus;
                    choice = staffInterface.scanInteger("Enter ID to set the showing status of the movie: ");
                    while (true) {
                        	if (choice == 1) {showingStatus = Movie.ShowingStatus.COMINGSOON;}
                        	else if (choice == 2) {showingStatus = Movie.ShowingStatus.PREVIEW;}
                        	else if (choice == 3) {showingStatus = Movie.ShowingStatus.NOWSHOWING;}
                        	else if (choice == 4) {

                                ArrayList indexStore = new ArrayList();
                                ArrayList<MovieShowing> indexStorers= new ArrayList<MovieShowing>();


                                for (MovieShowing movieshowing: movieShowings) {
                                    if (movieshowing.getMovie().getTitle().equals(movieToUpdate.getTitle())) {
                                    	indexStorers.add(movieshowing);
                                    }
                                }
                                for (MovieShowing store : indexStorers) {

                                	movieShowings.remove(store);  
                                }
                        		showingStatus = Movie.ShowingStatus.ENDOFSHOWING;}
                        	else {
                        		choice = staffInterface.scanInteger("Wrong input, please enter a valid ID: ");
                        		continue;
                        	}
                            break;

                        }
                    newMovie.setShowingStatus(showingStatus);
                    break;

                case 2:
                    staffInterface.displayLine(
                            "\nType of movie:\n" +
                            "1) Digital\n" +
                            "2) Blockbuster\n" +
                            "3) Normal");
                    Movie.TypeOfMovie typeOfMovie;
                    choice = staffInterface.scanInteger("Enter ID to set the type of movie: ");
                    while (true) {
                                if (choice == 1) {typeOfMovie  = Movie.TypeOfMovie.DIGITAL;
                                for(MovieShowing movieShowing : movieShowings) {
                                	movieShowing.getMovie().setTypeOfMovie(typeOfMovie.DIGITAL);
                                }
                                }
                            	else if (choice == 2) {typeOfMovie = Movie.TypeOfMovie.BLOCKBUSTER;
                            	for(MovieShowing movieShowing : movieShowings) {
                                	movieShowing.getMovie().setTypeOfMovie(typeOfMovie.BLOCKBUSTER);
                                }}
                            	else if (choice == 3) {typeOfMovie = Movie.TypeOfMovie.NORMAL;
                            	for(MovieShowing movieShowing : movieShowings) {
                                	movieShowing.getMovie().setTypeOfMovie(typeOfMovie.NORMAL);
                                }}
                            	else {
                            		choice = staffInterface.scanInteger("Wrong input, please enter a valid ID: ");
                            		continue;
                            	}
                            	break;
                    }
                    newMovie.setTypeOfMovie(typeOfMovie);
                    break;
                case 3:
                    synopsis = staffInterface.scanLine("Input new synopsis: ");
                    newMovie.setSynopsis(synopsis);
                    break;
                default:
                    staffInterface.displayLine("Wrong input, please try again");
            }
            staffInterface.displayLine(
            "Choose the field that you want to change:\n" +
            "1) Showing status\n" +
            "2) Type of movie\n" +
            "3) Synopsis\n" +
            "4) Finish modifying");

            choice = staffInterface.scanInteger("");
        }
        staffInterface.displayLine("Movie: " + movieToUpdate.getTitle() +" updated\n");

        movies.set(movies.indexOf(movieToUpdate), newMovie);
    }
    /**
     * Remove movie listing<br>
     * Lists all movies and staff can remove by index.
     */
    public void removeMovieListing() {
        int index;
        Movie movieToRemove; 

        index = listAndSelectMovieWithoutDetails();
        movieToRemove = movies.get(index);
        ArrayList indexStore = new ArrayList();
        ArrayList<MovieShowing> indexStorers= new ArrayList<MovieShowing>();


        for (MovieShowing movieshowing: movieShowings) {
            if (movieshowing.getMovie().getTitle().equals(movieToRemove.getTitle())) {
            	indexStorers.add(movieshowing);
            }
        }
        for (MovieShowing store : indexStorers) {

        	movieShowings.remove(store);  
        }
        staffInterface.displayLine("Movie: " + movieToRemove.getTitle() +" removed\n");

        movieToRemove.setShowingStatus(Movie.ShowingStatus.ENDOFSHOWING);

    }
    /**
     * List top 5 movies by sale
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
	
	      staffInterface.displayLine("The top 5 movies by sales are:");
	      for (Movie movie: topMovies) {
	    	  staffInterface.displayLine(topMovies.indexOf(movie)+1 + ") " + movie.getTitle()  + ", Total number of tickets sold: " + movie.getRevenue());
	      }
	  }

    
	    /**
	     * List top 5 movies by rating
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
	
	      staffInterface.displayLine("The top 5 movies by Ratings are:");
	      for (Movie movie: topMovies) {
	    	  staffInterface.displayLine(topMovies.indexOf(movie)+1 + ") " + movie.getTitle()  + ", Rating: " + movie.getRating());
	      }
    }
    /**
     * Creates movie showtime<br>
     * Lists all movies, staff selects which movie to create showtime by index<br>
     */
    public void createShowtime() {
        Movie movie;
        Cinema cinema;
        Cineplex cineplex;
        String showTimeString;
        Date showTime = null;
        int index;

        movie = listAndSelectMovieWithoutDetailsWithoutENDSHOWING();

        index = listAndSelectCineplex();
        cineplex = cineplexes.get(index);

        index = listAndSelectCinema(cineplex);
        cinema = cineplex.getCinema().get(index);
        
        Seat seat1[] = new Seat[1000];
        for(int j=0;j<1000;j++) {
    		seat1[j] = new Seat(enumSeat.NOTTAKEN, "1");
        }
        
        Cinema cinema1 = new Cinema(cinema.getClassOfCinema(), cinema.getCinemaCode(), 10, 10, seat1, cinema.getCinemaIndex(), 100);

        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        fmt.setLenient(false);
        while (true) {
            showTimeString = staffInterface.scanLine("Input showtime in format DD/MM/YYYY HH:MM: ");
            try {
                showTime = fmt.parse(showTimeString);
                break;
            } catch (ParseException e) {
                staffInterface.displayLine("Wrong format, please try again");
            }
        }
        staffInterface.displayLine("New showtime for " + movie.getTitle() + " added!");
        movieShowings.add(new MovieShowing(movie, cinema1, cineplex, showTime));
    }
    /**
     * Updates movie showtime<br>
     * Lists all movies, staff selects which movie to update showtime by index<br>
     */
    public void updateShowtime() {
        int index;
        String newShowTimeString;
        Date newShowTime = null;
        MovieShowing movieShowingToUpdate = null;

        int counter = 1;
        for (MovieShowing movieShowing: movieShowings) {
        	
        	staffInterface.display("\n" + counter + ") "+ movieShowing.displayMovieInfo() + "\n");
        	counter++;
        }
        
        index = staffInterface.scanInteger("\nChoose the show time to edit: ");
        counter = 1;
        for (MovieShowing movieShowing: movieShowings) {
        	if(counter == index) {
        		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                fmt.setLenient(false);
                while (true) {
                    newShowTimeString = staffInterface.scanLine("Input the new show time in format DD/MM/YYYY HH:MM: ");
                    try {
                        newShowTime = fmt.parse(newShowTimeString);
                        break;
                    } catch (ParseException e) {
                        staffInterface.displayLine("Wrong format, please try again");
                    }
                }

                movieShowing.setShowTime(newShowTime);
                staffInterface.displayLine("Showtime for " + movieShowing.getMovie().getTitle() + " updated!");
                break;
        	} counter++;

        }
       
    }
    
    /**
     * Remove movie showtime<br>
     * Lists all movies, staff selects which movie to remove showtime by index<br>
     * Remove showtime by entering showtime to remove<br>
     */
    public void removeShowtime() {
    	int chosen;
        int counter = 1;
        for (MovieShowing movieShowing: movieShowings) {
        	
        	staffInterface.display("\n" + counter + ") "+ movieShowing.displayMovieInfo() + "\n");
        	counter++;
        }
        
        chosen = staffInterface.scanInteger("\nChoose the show time to remove: ");
        counter = 1;
        for (MovieShowing movieShowing: movieShowings) {
        	if(counter == chosen) {
                movieShowings.remove(movieShowings.indexOf(movieShowing));
                staffInterface.displayLine("Showtime for " + movieShowing.getMovie().getTitle() + " removed!");
                break;
        	} counter++;

        }
    }
    /**
     * Updates ticket price
     */
    public void updateTicketPrice() {
        int index;
        double price;
        Movie movieToUpdate;

        index = ListMoviesPrice();
        movieToUpdate = movies.get(index);

        while (true) {
            price = staffInterface.scanDouble("Please input the new base price: ");
            if (price > 0) {
                break;
            } else {
                staffInterface.displayLine("Price cannot be non-positive, please try again");
            }
        }
        staffInterface.displayLine("Ticket price for " + movieToUpdate.getTitle() + " updated!");
        movieToUpdate.setBasePrice(price);
    }
    
    /**
     * List movies with price
     */
    public int ListMoviesPrice() {
        int index;

        for (Movie movie: movies) {
            staffInterface.displayLine("\n" + (movies.indexOf(movie)+1) + ") Title: " + movie.getTitle() + 
            		"\n" + "   Base Price: " + movie.getBasePrice() + " SGD");
        }

        while (true) {
            try {
                index = staffInterface.scanInteger("\nPlease enter the movie ID: ");
                movies.get(index-1);
                break;
            } catch (IndexOutOfBoundsException e) {
                staffInterface.displayLine("Wrong index, please try again");
            }
        }

        return index-1;
    }
    /**
     * Configures Holidays<br>
     * Functionality includes:
     * <ul>
     * <li>View holidays</li>
     * <li>Add holiday</li>
     * <li>Remove holiday</li>
     * </ul>
     */
    public void confirgureHoliday() {
    	int choice;
        staffInterface.displayLine(
        "\n===================================\n" +
        "        Holiday Confirguration      \n" +
        "===================================\n" +
        "1) View holidays\n" +
        "2) Add holiday\n" +
        "3) Remove holiday\n" +
        "4) Exit configuration");
        choice = staffInterface.scanInteger("\nEnter your choice: ");
        while (choice != 4) {
            switch (choice) 
            {
            case 1: viewHolidays(); break;
            case 2: addHoliday(); break;
            case 3: removeHoliday(); break;
            default:
                staffInterface.displayLine("Wrong input, please try again");
            
            }
            staffInterface.displayLine(
                    "\n===================================\n" +
                    "        Holiday Confirguration      \n" +
                    "===================================\n" +
                    "1) View holidays\n" +
                    "2) Add holiday\n" +
                    "3) Remove holiday\n" +
                    "4) Exit configuration");
        	choice = staffInterface.scanInteger("\nEnter your choice: ");
        }

    }
    
	/**
	 * View holidays
	 * @return ArrayList of Date objects
	 */
	public ArrayList<Date> viewHolidays()
    {
    	ArrayList<Date> dates = new ArrayList<Date>();
        int index;
        int size = holidays.size();
        if (size == 0) {
        	staffInterface.displayLine("\nThere are currently no holidays\n");
        	return dates;
        }
        staffInterface.displayLine("\nThis is the list of all holidays:\n");
        for (Date date: holidays) {
        	if (holidays.size() == 0) 
        	{
        		staffInterface.displayLine("There are currently no holidays");
        		break;
        	}
        	SimpleDateFormat  dateFormatter = new SimpleDateFormat("d MMMM yyyy, EEEE");
        	staffInterface.displayLine( ((holidays.indexOf(date))+1) + ") " + dateFormatter.format(date));
            dates.add(date);
        }
        
        return dates;
    }
    /**
     * Removes a holiday date
     */
    public void removeHoliday()
    {
    	int choice;
    	ArrayList<Date> dates = viewHolidays();
    	int size = dates.size();
        if (size == 0) {
        	return;
        }
        choice = staffInterface.scanInteger("Enter ID of the holiday you want to remove: ");
        while (choice > size | choice<1) {
            choice = staffInterface.scanInteger("Invalid input! Please try again: ");
            }
        staffInterface.displayLine("Holiday successfully removed!");
        holidays.remove(choice-1);
    }
    
    /**
     * Add Holidays<br>
     * Enter in format "dd/MM/yyyy"
     */
    public void addHoliday() {
        String holidayString;
        Date holiday;

        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        fmt.setLenient(false);
        while (true) {
            holidayString = staffInterface.scanString("Enter a holiday date in the format DD/MM/YYYY: ");
            try {
                holiday = fmt.parse(holidayString);
                break;
            } catch (ParseException e) {
                staffInterface.displayLine("Wrong format, please try again");
            }
        }
        staffInterface.displayLine("Holiday successfully added!");
        holidays.add(holiday);
    }
    /**
     * Logout
     */
    public void signOut() {
        if (loggedIn) {
            loggedIn = false;
            User.getInstance().setTypeOfUser(TypeOfUser.MOVIEGOER);
            staffInterface.displayLine("Logout successful");
        }
    }
    /**
     * List and select movie to modify
     * @return Movie index
     */
    public int listAndSelectMovie() {
        int index;

        for (Movie movie: movies) {
            staffInterface.displayLine(movies.indexOf(movie)+1 + ". " + movie.displayInfo());
        }

        while (true) {
            try {
                index = staffInterface.scanInteger("\nPlease enter the movie ID: ");
                movies.get(index);
                break;
            } catch (IndexOutOfBoundsException e) {
                staffInterface.displayLine("Wrong index, please try again");
            }
        }

        return index-1;
    }
    
    /**
     * List and select movie (prints without details)
     * @return movie index
     */
    public int listAndSelectMovieWithoutDetails() {
        int index;

        for (Movie movie: movies) {
            staffInterface.displayLine(movies.indexOf(movie)+1 + ". " + movie.getTitle());
        }

        while (true) {
            try {
                index = staffInterface.scanInteger("\nPlease enter the movie ID: ");
                movies.get(index-1);
                break;
            } catch (IndexOutOfBoundsException e) {
                staffInterface.displayLine("Wrong index, please try again");
            }
        }

        return index-1;
    }
    
    /**
     * List and selects movies (without details and shows that have not ended)
     * @return
     */
    public Movie listAndSelectMovieWithoutDetailsWithoutENDSHOWING() {
        int index;
        Movie movie1 = null;
        ArrayList<Movie> moviesWithoutENDSHOWING = new ArrayList<Movie>();
        int counter = 1;

        for (Movie movie: movies) {
        	if(movie.getShowingStatus() != ShowingStatus.ENDOFSHOWING) {
   
        		staffInterface.displayLine(counter + ". " + movie.getTitle());
        		moviesWithoutENDSHOWING.add(movie);
        		counter++;
        	}
            
        }

        while (true) {
            try {
                index = staffInterface.scanInteger("\nPlease enter the movie ID: ");
                moviesWithoutENDSHOWING.get(index-1);
                break;
            } catch (IndexOutOfBoundsException e) {
                staffInterface.displayLine("Wrong index, please try again");
            }
        }
        
        movie1 = moviesWithoutENDSHOWING.get(index-1);

        return movie1;
    }
    /**
     * List and select movie showtimes
     * @return movie showtime index
     */
    public int listAndSelectMovieShowing() {
        int index;

        for (MovieShowing movieShowing: movieShowings) {
            staffInterface.displayLine(movieShowings.indexOf(movieShowing)+1 + ". " + movieShowing.toString());
        }

        while (true) {
            try {
                index = staffInterface.scanInteger("\nPlease enter ID of the movie showing: ");
                movieShowings.get(index);
                break;
            } catch (IndexOutOfBoundsException e) {
                staffInterface.displayLine("Wrong index, please try again");
            }
        }

        return index-1;
    }
    /**
     * List and select the cineplex to modify
     * 
     * @return cineplex index
     */
    private int listAndSelectCineplex() {
        int index;

        for (Cineplex cineplex: cineplexes) {
            staffInterface.displayLine(cineplexes.indexOf(cineplex) + ") " + cineplex.getCineplexName());
        }

        while (true) {
            try {
                index = staffInterface.scanInteger("\nPlease enter ID of the cineplex: ");
                cineplexes.get(index);
                break;
            } catch (IndexOutOfBoundsException e) {
                staffInterface.displayLine("Wrong index, please try again");
            }
        }

        return index;
    }
    /**
     * List and select the cinema
     * @param cineplex Desired cineplex containing cinema to be selected
     * @return cinema index
     */
    private int listAndSelectCinema(Cineplex cineplex) {
    	ArrayList<Cinema> cinemas = cineplex.getCinema();
        Cinema cinema;
        int index, i;

        for (i=0; i<cinemas.size(); ++i) {
            staffInterface.displayLine(i + ". " + cinemas.get(i).getCinemaCode());
        }

        while (true) {
            try {
                index = staffInterface.scanInteger("\nPlease enter ID of the cinema: ");
                cinema = cinemas.get(index);
                break;
            } catch (IndexOutOfBoundsException e) {
                staffInterface.displayLine("Wrong index, please try again");
            }
        }

        return index;
    }
    /**
     * Handles staff login.<br>
     * <ul>
     * <li>If already logged in, returns ALREADYLOGGEDIN</li>
     * <li>Username and Password match, returns LOGINSUCCESS</li>
     * <li>If Username and Password do not match, returns WRONGUSERNAMEPASSWORD<li>
     * @param username Staff username
     * @param password Staff password
     * @return LoginFeedback (enum)
     * @see MovieGoerMgr#staffLogin()
     */
    public LoginFeedback login(String username, String password) {
        if (!loggedIn) {
            if (username.equals(staffUsername) && password.equals(staffPassword)) {
                loggedIn = true;
                User.getInstance().setTypeOfUser(TypeOfUser.STAFF);
                return LoginFeedback.LOGINSUCCESS;
            } else {
                return LoginFeedback.WRONGUSERNAMEPASSWORD;
            }
        } else {
            return LoginFeedback.ALREADYLOGGEDIN;
        }
    }
    /**
     * Generates sale report
     */
    public void getSalesReport() {
        Map<Date, Integer> saleReport = new TreeMap<Date, Integer>();
        String dateString;
        Date date = null;
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat fmt2 = new SimpleDateFormat("dd/MM/yyyy");

        // loop through all bookings to get sale stats
        for (Booking booking: bookings) {
            dateString = booking.getPaymentInfo().getTransactionID().substring(3, 11);
            try {
                date = fmt.parse(dateString);
            } catch (ParseException e) {
                staffInterface.displayLine("Error parsing date");
            }
            if (saleReport.containsKey(date)) {
                saleReport.put(date, saleReport.get(date) + 1);
            } else {
                saleReport.put(date, 1);
            }
        }

        staffInterface.displayLine("Sale report");
        for (Date dateInSaleReport: saleReport.keySet()) {
            if (saleReport.get(dateInSaleReport) > 1) {
                staffInterface.displayLine(fmt2.format(dateInSaleReport) + ": " + saleReport.get(dateInSaleReport) + " tickets sold");
            } else {
                staffInterface.displayLine(fmt2.format(dateInSaleReport) + ": " + saleReport.get(dateInSaleReport) + " ticket sold");
            }
        }
    }
}