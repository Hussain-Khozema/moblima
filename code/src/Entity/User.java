package Entity;

/**
 * User Class is used for accessing customer/staff functionality and to determine if user exits application.
 * Used in Manager Class.
 * @see Manager
 * @author SS3 Group 8
 * 
 */
public class User {

    /**
     * Enum for type of user
     * @author SS3 Group 8
     *
     */
    public enum TypeOfUser {STAFF, MOVIEGOER}

    /**
     * Sets user to null
     */
    private static User user = null;

    /**
     * Contains Type of User
     */
    private TypeOfUser typeOfUser;

    /**
     * Status
     */
    private boolean active;

    /**
     * Constructor for User
     */
    private User() {
        typeOfUser = TypeOfUser.MOVIEGOER;
        active = true;
    }

    /**
     * Returns instance of User. If instance does not exist,
     * creates a new user.
     * @return user
     */
    public static User getInstance() {
        if (user == null) {
            user = new User();
        }
        return user;
    }

    /**
     * Sets type of user
     * @param typeOfUser New Type of User (Enum)
     */
    public void setTypeOfUser(TypeOfUser typeOfUser) {
        this.typeOfUser = typeOfUser;
    }

    /**
     * Sets status to Active
     * @param active True/False
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Checks if User is a MovieGoer
     * @return True/False
     */
    public boolean isMovieGoer() {
        if (typeOfUser == TypeOfUser.MOVIEGOER)
            return true;
        return false;
    }

    /**
     * Checks if status is active
     * @return True/False
     */
    public boolean isActive() {
        return active;
    }
}
