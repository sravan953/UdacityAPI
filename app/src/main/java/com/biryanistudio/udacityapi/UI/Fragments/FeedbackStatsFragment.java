package com.biryanistudio.udacityapi.UI.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.biryanistudio.udacityapi.Interfaces.IUpdateFeedback;
import com.biryanistudio.udacityapi.Interfaces.IUpdateFeedbackStats;
import com.biryanistudio.udacityapi.Models.FeedbackStats;
import com.biryanistudio.udacityapi.Tasks.FeedbackStatsTask;
import com.biryanistudio.udacityapi.Interfaces.IUpdateSubmissions;
import com.biryanistudio.udacityapi.Models.Submissions;
import com.biryanistudio.udacityapi.R;

import java.util.List;

/**
 * Created by Sravan on 07-Apr-16.
 */
public class FeedbackStatsFragment extends Fragment implements IUpdateFeedbackStats {
    View view;

    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.from(container.getContext()).inflate(R.layout.fragment_dummy, container, false);
        new FeedbackStatsTask().execute(this);
        return view;
    }

    @Override
    public void feedbackStatsUI(FeedbackStats feedbackStats) {
        ((TextView) view.findViewById(R.id.text)).setText(feedbackStats.toString());
    }
}
