package com.biryanistudio.udacityapi.Service;

import com.biryanistudio.udacityapi.Models.Feedback;
import com.biryanistudio.udacityapi.Models.FeedbackStats;
import com.biryanistudio.udacityapi.Models.Submissions;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Sravan on 05-Apr-16.
 */
public interface UdacityService {
    @GET("/api/v1/me/submissions.json")
    Call<List<Submissions>> getSubmissions();

    @GET("/api/v1/me/student_feedbacks.json")
    Call<List<Feedback>> getFeedback();

    @GET(" /api/v1/me/student_feedbacks/stats.json")
    Call<FeedbackStats> getFeedbackStats();
}
