package com.biryanistudio.udacityapi.Models;

/**
 * Created by Sravan on 07-Apr-16.
 */
public class FeedbackStats {
    private int unread_count; // (number, optional): number of feedbacks not seen by reviewer (no date limitation)
    private int recent_count; // (number, optional): number of feedbacks created within the last 30 days

    @Override
    public String toString() {
        return ("unread_count: " + unread_count) +
                "\n" +
                "recent_count: " + recent_count;

    }
}
