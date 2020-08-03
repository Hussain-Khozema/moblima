package Control;

import java.math.BigDecimal;

import Entity.Movie;
import Entity.Review;
import Entity.User;

/**
 * Used for calculating rating
 * 
 * @see MovieGoerMgr#addReview()
 * @author SS3 Group 8
 *
 */
public class PersonMgr {
    /**
     * Calculate the overall rating for the movie
     * @param movie Movie object for which Rating needs to be calculated
     */

    public void calculateRating(Movie movie) {
        int sum = 0;
        for (Review review: movie.getReviews()) {
            sum += review.getRating();
        }
        if (movie.getReviews().size() == 0) {
        	return;
        }
        double finalRating = (double)sum / movie.getReviews().size();
        finalRating = new BigDecimal(finalRating ).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        movie.setRating(finalRating);
    }
    /**
     * Set user status as inactive
     */
    public void exit() {
        User.getInstance().setActive(false);
    }
}