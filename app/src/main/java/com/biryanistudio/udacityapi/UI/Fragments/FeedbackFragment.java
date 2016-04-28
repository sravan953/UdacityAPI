package com.biryanistudio.udacityapi.UI.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.biryanistudio.udacityapi.Interfaces.IUpdateFeedback;
import com.biryanistudio.udacityapi.Models.Feedback;
import com.biryanistudio.udacityapi.R;
import com.biryanistudio.udacityapi.Tasks.FeedbackTask;
import com.biryanistudio.udacityapi.UI.Adapters.FeedbackAdapter;

import java.util.List;

/**
 * Created by Sravan on 07-Apr-16.
 */
public class FeedbackFragment extends Fragment implements IUpdateFeedback {
    private ListView listView;
    private FeedbackAdapter feedbackAdapter;

    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);
        listView = (ListView) view.findViewById(R.id.listView);
        new FeedbackTask().execute(this);
        return view;
    }

    @Override
    public void feedbackUI(List<Feedback> feedbackList) {
        feedbackAdapter = new FeedbackAdapter(getActivity(), R.layout.item_feedback, feedbackList);
        listView.setAdapter(feedbackAdapter);
    }
}
