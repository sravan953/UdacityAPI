package com.biryanistudio.udacityapi.Models;

/**
 * Created by Sravan on 28-Apr-16.
 */
public class Certification extends GenericModel {
    int id; // (integer, optional)
    String status; // (string, optional) = ['applied', 'shortlisted', 'training', 'certified', 'inactive', 'blocked']
    boolean active; // (boolean, optional)
    String created_at; // (string, optional)
    String updated_at; // (string, optional)
    String waitlisted_at; // (string, optional)
    String certified_at; // (string, optional)
    int project_id; // (integer, optional)
    int grader_id; // (integer, optional)
    int trainings_count; // (integer, optional)
    Project project; // (project, optional)

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
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("id: "+ String.valueOf(id));
        stringBuilder.append("\n");
        stringBuilder.append("status: "+ status);
        stringBuilder.append("\n");
        stringBuilder.append("active: "+ String.valueOf(active));
        stringBuilder.append("\n");
        stringBuilder.append("created_at: "+ created_at);
        stringBuilder.append("\n");
        stringBuilder.append("updated_at: "+ updated_at);
        stringBuilder.append("\n");
        stringBuilder.append("waitlisted_at: "+ waitlisted_at);
        stringBuilder.append("\n");
        stringBuilder.append("certified_at: "+ certified_at);
        stringBuilder.append("\n");
        stringBuilder.append("project_id: "+ String.valueOf(project_id));
        stringBuilder.append("\n");
        stringBuilder.append("grader_id: "+ String.valueOf(grader_id));
        stringBuilder.append("\n");
        stringBuilder.append("trainings_count: "+ String.valueOf(trainings_count));
        stringBuilder.append("\n");
        stringBuilder.append("project: "+ project.getProjectName());
        stringBuilder.append("\n");
        stringBuilder.append("awaiting review: "+ project.getAwaitingReviewCount());
        return stringBuilder.toString();
    }
}
