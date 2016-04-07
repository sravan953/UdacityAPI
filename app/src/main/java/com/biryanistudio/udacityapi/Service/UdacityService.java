package com.biryanistudio.udacityapi.Service;

import com.biryanistudio.udacityapi.Models.Feedback;
import com.biryanistudio.udacityapi.Models.FeedbackStats;
import com.biryanistudio.udacityapi.Models.Submissions;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by Sravan on 05-Apr-16.
 */
public interface UdacityService {
    @Headers({
            "Authorization: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjozODA5LCJleHAiOjE0NjIyOTE2OTksInRva2VuX3R5cGUiOiJhcGkifQ.PXZY0UkiqEsh8fRfXNBeVwO-Wbb4teFBD2MIOcJDXO4",
            "Content-Length: 0"
    })
    @GET("/api/v1/me/submissions.json")
    Call<List<Submissions>> getSubmissions();

    @Headers({
            "Authorization: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjozODA5LCJleHAiOjE0NjIyOTE2OTksInRva2VuX3R5cGUiOiJhcGkifQ.PXZY0UkiqEsh8fRfXNBeVwO-Wbb4teFBD2MIOcJDXO4",
            "Content-Length: 0"
    })
    @GET("/api/v1/me/student_feedbacks.json")
    Call<List<Feedback>> getFeedback();

    @Headers({
            "Authorization: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjozODA5LCJleHAiOjE0NjIyOTE2OTksInRva2VuX3R5cGUiOiJhcGkifQ.PXZY0UkiqEsh8fRfXNBeVwO-Wbb4teFBD2MIOcJDXO4",
            "Content-Length: 0"
    })
    @GET(" /api/v1/me/student_feedbacks/stats.json")
    Call<FeedbackStats> getFeedbackStats();
}
