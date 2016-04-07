package com.biryanistudio.udacityapi.Models;

import org.json.JSONObject;

/**
 * Created by Sravan on 07-Apr-16.
 */
public class Submissions {
    int id; // id of the submission for Reviews API
    String repo_url; // URL of the Github repo, if available
    String commit_sha; // SHA1 hash from the Github repo on creation of submission
    boolean hidden; // true if submission is a deep copied and revised submission
    int previous_submission_id; // id of the prior submission that was revised
    String created_at; // datestamp on creation
    String updated_at; // datestamp on last modification
    String assigned_at; // datestamp on assignment to a reviewer
    String completed_at;// datestamp on completion of review
    int grader_id; // id of assigned grader, if any
    String status; // ['processing', 'awaiting_review', 'in_review', 'completed', 'erred']
    String status_reason; // empty for successful submissions
    String udacity_key; // identifier of submission on Udacity API
    int training_id; // null for regular submissions
    String general_comment; // Comment on the review by the reviewer
    String notes; // Notes on the submission provided by the student
    JSONObject user; // Name of the student
    int user_id; // student ID
    JSONObject grader; // Name of the grader
    //int grader_id; // reviewer ID
    String[] annotation_urls; // URLs of annotation PDFs supplied by reviewer
    int project_id; // id of the project certification applies to
    //project; // abbreviated project record submission applies to
    String price; // e.g. "25.0" Price to be paid, recorded at time assigned
    String result; // Result of review ['ungradeable', 'passed', 'failed', 'exceeded']
    String result_reason; // Reason the submission was ungradeable
    String nomination; // Submission excellence nomination text

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
        stringBuilder.append("status: " + status);
        stringBuilder.append("\n");
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
