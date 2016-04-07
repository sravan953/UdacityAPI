package com.biryanistudio.udacityapi.UI.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.biryanistudio.udacityapi.Tasks.FeedbackTask;
import com.biryanistudio.udacityapi.Interfaces.IUpdateFeedback;
import com.biryanistudio.udacityapi.Models.Feedback;
import com.biryanistudio.udacityapi.R;

import java.util.List;

/**
 * Created by Sravan on 07-Apr-16.
 */
public class FeedbackFragment extends Fragment implements IUpdateFeedback {
    View view;

    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.from(container.getContext()).inflate(R.layout.fragment_dummy, container, false);
        new FeedbackTask().execute(this);
        return view;
    }

    @Override
    public void feedbackUI(List<Feedback> feedbacks) {
        StringBuilder builder = new StringBuilder();
        for(Feedback feedback : feedbacks) {
            builder.append(feedback.toString());
            builder.append("\n");
        }
        ((TextView) view.findViewById(R.id.text)).setText(builder.toString());
    }
}
