package com.biryanistudio.udacityapi.Models;

/**
 * Created by Sravan on 05-Apr-16.
 */
public class Feedback {
    int project_id; // ID of project
    //String[] project; // name of associated project
    int submission_id; // ID of submission
    int user_id; // ID of student
    String body; // optional text comment with feedback
    int rating; // 1-5 star rating
    int grader_id; // ID of reviewer
    String created_at; // datestamp of feedback created
    String read_at; // datestamp of feedback viewed by reviewer
    String updated_at; // datestamp of last modification

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("project_id: " + project_id);
        stringBuilder.append("\n");
        //stringBuilder.append("project: " + project[0]);
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
