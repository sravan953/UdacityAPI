package com.biryanistudio.udacityapi.Models;

import org.json.JSONException;

/**
 * Created by Sravan on 07-Apr-16.
 */
public class Submission extends GenericModel {
    int id; // (number, optional): id of the submission for Reviews API
    String repo_url; // (string, optional): URL of the Github repo, if available
    String commit_sha; // (string, optional): SHA1 hash from the Github repo on creation of submission
    boolean hidden; // (boolean, optional): true if submission is a staff revision of a prior submission
    int previous_submission_id; // (number, optional): ID of the prior submission that was revise
    String created_at; // (string, optional): Datestamp on creation YYYY-MM-ddTHH:MM:ss.uuuz
    String updated_at; // (string, optional): Datestamp on last modification YYYY-MM-ddTHH:MM:ssz
    String assigned_at; // (string, optional): Datestamp on assignment to a reviewer YYYY-MM-ddTHH:MM:ssz
    String completed_at; // (string, optional): Datestamp on completion of review YYYY-MM-ddTHH:MM:ssz
    int grader_id; // (number, optional): ID of assigned grader, if any
    //status (object, optional): Status of submission = ['processing', 'awaiting_review', 'in_review', 'completed', 'erred']
    String status_reason; // (string, optional): Error reason if the submission has an error status
    String udacity_key; // (string, optional): identifier of submission on Udacity API
    int training_id; // (number, optional): ID of the training if this a training submission
    String general_comment; // (string, optional): Comment on the review by the reviewer
    String notes; // (string, optional): Notes on the submission provided by the student
    User user; // (userName, optional)
    int user_id; // (number, optional): ID of the student
    User grader; // (userName, optional)
    String[] annotation_urls; //  (Array[string], optional): URLs of annotation PDFs supplied by reviewer
    int project_id; // (number, optional): ID of the project of this submission
    Project project; // (project, optional)
    String price; // (string, optional): Price to be paid, recorded at time assigned
    String result; // (string, optional): Result of review = ['ungradeable', 'passed', 'failed', 'exceeded']
    String result_reason; // (string, optional): Reason the submission was ungradeable
    String nomination; //  (string, optional): Submission excellence nomination text
    //String zipfile; // (string, optional): used for creation ,
    //String files; // (string, optional): used for creation
    String held_at; // (string, optional): deprecated
    String archive_url; // (string, optional): S3 URL for the submission zip file
    String url; // (string, optional): URL to be reviewed. Typically a LinkedIn or Github profile

    public String getProject() throws JSONException {
        return project.getProjectName();
    }

    public String getRate() {
        return price;
    }

    public String getAssignedAt() {
        return assigned_at;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("id: " + id);
        stringBuilder.append("\n");
        stringBuilder.append("repo_url: " + repo_url);
        stringBuilder.append("\n");
        stringBuilder.append("commit_sha: " + commit_sha);
        stringBuilder.append("\n");
        stringBuilder.append("hidden: " + hidden);
        stringBuilder.append("\n");
        stringBuilder.append("previous_submission_id: " + previous_submission_id);
        stringBuilder.append("\n");
        stringBuilder.append("created_at: " + created_at);
        stringBuilder.append("\n");
        stringBuilder.append("updated_at: " + updated_at);
        stringBuilder.append("\n");
        stringBuilder.append("assigned_at: " + assigned_at);
        stringBuilder.append("\n");
        stringBuilder.append("completed_at: " + completed_at);
        stringBuilder.append("\n");
        stringBuilder.append("grader_id: " + grader_id);
        stringBuilder.append("\n");
        //stringBuilder.append("status: " + status);
        //stringBuilder.append("\n");
        stringBuilder.append("status_reason: " + status_reason);
        stringBuilder.append("\n");
        stringBuilder.append("udacity_key: " + udacity_key);
        stringBuilder.append("\n");
        stringBuilder.append("training_id: " + training_id);
        stringBuilder.append("\n");
        stringBuilder.append("general_comment: " + general_comment);
        stringBuilder.append("\n");
        stringBuilder.append("notes: " + notes);
        stringBuilder.append("\n");
        stringBuilder.append("user: " + user.toString());
        stringBuilder.append("\n");
        stringBuilder.append("project_id: " + project_id);
        stringBuilder.append("\n");
        stringBuilder.append("price: " + price);
        stringBuilder.append("\n");
        stringBuilder.append("result: " + result);
        stringBuilder.append("\n");
        stringBuilder.append("result_reason: " + result_reason);
        stringBuilder.append("\n");
        stringBuilder.append("nomination: " + nomination);
        return stringBuilder.toString();
    }
}
