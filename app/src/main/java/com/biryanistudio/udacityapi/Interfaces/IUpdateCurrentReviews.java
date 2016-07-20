package com.biryanistudio.udacityapi.Interfaces;

import com.biryanistudio.udacityapi.Models.Submission;

import java.util.List;

/**
 * Created by Sravan on 07-Apr-16.
 */
public interface IUpdateCurrentReviews {
    void currentReviewsUI(final List<Submission> submissions);
}
