package com.biryanistudio.udacityapi.Models;

import org.json.JSONException;

/**
 * Created by Sravan on 05-Apr-16.
 */
public class Feedback extends BaseModel {
    int project_id; // (integer, optional): ID of the project
    Project project; // (projectName, optional)
    int submission_id; // (integer, optional): ID of the submission
    int user_id; // (integer, optional): ID of the student
    String body; // (string, optional): Optional text comment from the student
    int rating; // (integer, optional): 1-5 star rating
    int grader_id; // (integer, optional): ID of the reviewer
    String created_at; // (string, optional): datestamp at time of feedback creation
    String read_at; // (string, optional): datestamp at time the feedback was displayed to the reviewer
    String updated_at; // (string, optional): datestamp at time the feedback was last modified

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
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("project_id: " + project_id);
        stringBuilder.append("\n");
        stringBuilder.append("project: " + project.getProjectName());
        stringBuilder.append("\n");
        stringBuilder.append("submission_id: " + submission_id);
        stringBuilder.append("\n");
        stringBuilder.append("user_id: " + user_id);
        stringBuilder.append("\n");
        stringBuilder.append("body: " + body);
        stringBuilder.append("\n");
        stringBuilder.append("rating: " + rating);
        stringBuilder.append("\n");
        stringBuilder.append("grader_id: " + grader_id);
        stringBuilder.append("\n");
        stringBuilder.append("created_at: " + created_at);
        stringBuilder.append("\n");
        stringBuilder.append("read_at: " + read_at);
        stringBuilder.append("\n");
        stringBuilder.append("updated_at: " + updated_at);
        return stringBuilder.toString();
    }
}
