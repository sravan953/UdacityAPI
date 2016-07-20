package com.biryanistudio.udacityapi.Models;

/**
 * Created by Sravan on 27-Apr-16.
 */
public class Project {
    private int id; // (integer, optional)
    private String name; // (string, optional)
    private String description; // (string, optional)
    private String required_skills; // (string, optional)
    private int awaiting_review_count; // (integer, optional)
    private String hashtag; // (string, optional)
    private boolean visible; // (boolean, optional)
    private int audit_project_id; // (integer, optional)
    private String[] upload_types; // (Array[string], optional)

    String getProjectName() {
        return name;
    }

    int getAwaitingReviewCount() {
        return awaiting_review_count;
    }

    int getProjectID() {
        return id;
    }
}
