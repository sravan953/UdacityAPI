package com.biryanistudio.udacityapi.Models;

import org.json.JSONException;

/**
 * Created by Sravan on 07-Apr-16.
 */
public class Submission {
    private int id; // (number, optional): id of the submission for Reviews API
    private String repo_url; // (string, optional): URL of the Github repo, if available
    private String commit_sha; // (string, optional): SHA1 hash from the Github repo on creation of submission
    private boolean hidden; // (boolean, optional): true if submission is a staff revision of a prior submission
    private int previous_submission_id; // (number, optional): ID of the prior submission that was revise
    private String created_at; // (string, optional): Datestamp on creation YYYY-MM-ddTHH:MM:ss.uuuz
    private String updated_at; // (string, optional): Datestamp on last modification YYYY-MM-ddTHH:MM:ssz
    private String assigned_at; // (string, optional): Datestamp on assignment to a reviewer YYYY-MM-ddTHH:MM:ssz
    private String completed_at; // (string, optional): Datestamp on completion of review YYYY-MM-ddTHH:MM:ssz
    private int grader_id; // (number, optional): ID of assigned grader, if any
    //status (object, optional): Status of submission = ['processing', 'awaiting_review', 'in_review', 'completed', 'erred']
    private String status_reason; // (string, optional): Error reason if the submission has an error status
    private String udacity_key; // (string, optional): identifier of submission on Udacity API
    private int training_id; // (number, optional): ID of the training if this a training submission
    private String general_comment; // (string, optional): Comment on the review by the reviewer
    private String notes; // (string, optional): Notes on the submission provided by the student
    private User user; // (userName, optional)
    private int user_id; // (number, optional): ID of the student
    private User grader; // (userName, optional)
    private String[] annotation_urls; //  (Array[string], optional): URLs of annotation PDFs supplied by reviewer
    private int project_id; // (number, optional): ID of the project of this submission
    private Project project; // (project, optional)
    private String price; // (string, optional): Price to be paid, recorded at time assigned
    private String result; // (string, optional): Result of review = ['ungradeable', 'passed', 'failed', 'exceeded']
    private String result_reason; // (string, optional): Reason the submission was ungradeable
    private String nomination; //  (string, optional): Submission excellence nomination text
    //String zipfile; // (string, optional): used for creation ,
    //String files; // (string, optional): used for creation
    private String held_at; // (string, optional): deprecated
    private String archive_url; // (string, optional): S3 URL for the submission zip file
    private String url; // (string, optional): URL to be reviewed. Typically a LinkedIn or Github profile

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
        //stringBuilder.append("status: " + status);
        //stringBuilder.append("\n");
        return ("id: " + id) +
                "\n" +
                "repo_url: " + repo_url +
                "\n" +
                "commit_sha: " + commit_sha +
                "\n" +
                "hidden: " + hidden +
                "\n" +
                "previous_submission_id: " + previous_submission_id +
                "\n" +
                "created_at: " + created_at +
                "\n" +
                "updated_at: " + updated_at +
                "\n" +
                "assigned_at: " + assigned_at +
                "\n" +
                "completed_at: " + completed_at +
                "\n" +
                "grader_id: " + grader_id +
                "\n" +
                "status_reason: " + status_reason +
                "\n" +
                "udacity_key: " + udacity_key +
                "\n" +
                "training_id: " + training_id +
                "\n" +
                "general_comment: " + general_comment +
                "\n" +
                "notes: " + notes +
                "\n" +
                "user: " + user.toString() +
                "\n" +
                "project_id: " + project_id +
                "\n" +
                "price: " + price +
                "\n" +
                "result: " + result +
                "\n" +
                "result_reason: " + result_reason +
                "\n" +
                "nomination: " + nomination;
    }
}
