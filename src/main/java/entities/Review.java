package entities;

import java.time.LocalDate;

public class Review {
    /**
     * The User writing the review
     */
    public User reviewer;

    /**
     * The User being reviewed
     */
    public User reviewed;

    /**
     * The integer rating given to the reviewed
     */
    public int rating;

    /**
     * The date this Review was written
     */
    public LocalDate date;

    /**
     * The constructor for the Review class
     *
     * @param reviewer the User who wrote the review
     * @param reviewed the User being reviewed
     * @param rating   the rating given to the reviewed User
     */
    public Review(User reviewer, User reviewed, int rating) {
        this.reviewer = reviewer;
        this.reviewed = reviewed;
        this.rating = rating;
        this.date = LocalDate.now();
    }
    /**
     * The constructor for the Review class
     *
     * @param rating   the rating given to the reviewed User
     */
    public Review(int rating) {
        this.rating = rating;
    }

    public User getReviewer() {
        return reviewer;
    }

    public User getReviewed() {
        return reviewed;
    }

    public int getRating() {
        return rating;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }

    public void setReviewed(User reviewed) {
        this.reviewed = reviewed;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
