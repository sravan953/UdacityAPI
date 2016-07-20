package com.biryanistudio.udacityapi.Models;

import org.json.JSONException;

/**
 * Created by Sravan on 05-Apr-16.
 */
public class Feedback {
    private int project_id; // (integer, optional): ID of the project
    private Project project; // (projectName, optional)
    private int submission_id; // (integer, optional): ID of the submission
    private int user_id; // (integer, optional): ID of the student
    private String body; // (string, optional): Optional text comment from the student
    private int rating; // (integer, optional): 1-5 star rating
    private int grader_id; // (integer, optional): ID of the reviewer
    private String created_at; // (string, optional): datestamp at time of feedback creation
    private String read_at; // (string, optional): datestamp at time the feedback was displayed to the reviewer
    private String updated_at; // (string, optional): datestamp at time the feedback was last modified

    public String getProject() throws JSONException {
        return project.getProjectName();
    }

    public String getBody() {
        return body;
    }

    public int getRating() {
        return rating;
    }

    public String getUpdatedAt() {
        return updated_at;
    }

    @Override
    public String toString() {
        return ("project_id: " + project_id) +
                "\n" +
                "project: " + project.getProjectName() +
                "\n" +
                "submission_id: " + submission_id +
                "\n" +
                "user_id: " + user_id +
                "\n" +
                "body: " + body +
                "\n" +
                "rating: " + rating +
                "\n" +
                "grader_id: " + grader_id +
                "\n" +
                "created_at: " + created_at +
                "\n" +
                "read_at: " + read_at +
                "\n" +
                "updated_at: " + updated_at;
    }
}
