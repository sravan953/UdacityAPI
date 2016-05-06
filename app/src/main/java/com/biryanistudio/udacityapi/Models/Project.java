package com.biryanistudio.udacityapi.Models;

/**
 * Created by Sravan on 27-Apr-16.
 */
public class Project {
    int id; // (integer, optional)
    String name; // (string, optional)
    String description; // (string, optional)
    String required_skills; // (string, optional)
    int awaiting_review_count; // (integer, optional)
    String hashtag; // (string, optional)
    boolean visible; // (boolean, optional)
    int audit_project_id; // (integer, optional)
    String[] upload_types; // (Array[string], optional)

    public String getProjectName() {
        return name;
    }

    public int getAwaitingReviewCount() {
        return awaiting_review_count;
    }

    public int getProjectID() {
        return id;
    }
}
