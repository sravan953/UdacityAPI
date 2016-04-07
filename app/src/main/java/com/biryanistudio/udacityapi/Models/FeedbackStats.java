package com.biryanistudio.udacityapi.Models;

/**
 * Created by Sravan on 07-Apr-16.
 */
public class FeedbackStats {
    int unread_count; // number of feedbacks not seen by reviewer (no date limitation)
    int recent_count; // number of feedbacks created within the last 30 days

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("unread_count: " + unread_count);
        stringBuilder.append("\n");
        stringBuilder.append("recent_count: " + recent_count);
        return stringBuilder.toString();

    }
}
