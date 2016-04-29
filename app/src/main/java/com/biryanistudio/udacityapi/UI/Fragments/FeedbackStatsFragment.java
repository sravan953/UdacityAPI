package com.biryanistudio.udacityapi.UI.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.biryanistudio.udacityapi.Interfaces.IUpdateFeedbackStats;
import com.biryanistudio.udacityapi.Models.FeedbackStats;
import com.biryanistudio.udacityapi.R;
import com.biryanistudio.udacityapi.Tasks.FeedbackStatsTask;

/**
 * Created by Sravan on 07-Apr-16.
 */
public class FeedbackStatsFragment extends Fragment implements IUpdateFeedbackStats {
    View view;

    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_feedback, container, false);
        new FeedbackStatsTask().execute(this);
        return view;
    }

    @Override
    public void feedbackStatsUI(FeedbackStats feedbackStats) {
        ((TextView) view.findViewById(R.id.text)).setText(feedbackStats.toString());
    }
}
