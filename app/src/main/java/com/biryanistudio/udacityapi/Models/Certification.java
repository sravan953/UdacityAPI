package com.biryanistudio.udacityapi.Models;

/**
 * Created by Sravan on 28-Apr-16.
 */
public class Certification {
    private int id; // (integer, optional)
    private String status; // (string, optional) = ['applied', 'shortlisted', 'training', 'certified', 'inactive', 'blocked']
    private boolean active; // (boolean, optional)
    private String created_at; // (string, optional)
    private String updated_at; // (string, optional)
    private String waitlisted_at; // (string, optional)
    private String certified_at; // (string, optional)
    private int project_id; // (integer, optional)
    private int grader_id; // (integer, optional)
    private int trainings_count; // (integer, optional)
    private Project project; // (project, optional)

    public String getProjectName() {
        return project.getProjectName();
    }

    public int getAwaitingReviewCount() {
        return project.getAwaitingReviewCount();
    }

    public int getProjectID() {
        return project.getProjectID();
    }

    @Override
    public String toString() {
        return ("id: " + String.valueOf(id)) +
                "\n" +
                "status: " + status +
                "\n" +
                "active: " + String.valueOf(active) +
                "\n" +
                "created_at: " + created_at +
                "\n" +
                "updated_at: " + updated_at +
                "\n" +
                "waitlisted_at: " + waitlisted_at +
                "\n" +
                "certified_at: " + certified_at +
                "\n" +
                "project_id: " + String.valueOf(project_id) +
                "\n" +
                "grader_id: " + String.valueOf(grader_id) +
                "\n" +
                "trainings_count: " + String.valueOf(trainings_count) +
                "\n" +
                "project: " + project.getProjectName() +
                "\n" +
                "awaiting review: " + project.getAwaitingReviewCount();
    }
}
